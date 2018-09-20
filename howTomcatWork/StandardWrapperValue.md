
> 1. standardWrapperValue 是 StanderWrapper运行时使用的基础阀
> 2. standardWrapperValue 会在这里通过 StanderWrapper 加载Servlet，并且调用里面的service方法
> 3. standardWrapperValue 会在这里完成设置的Filter中的过滤方法的调用，并且在调用完成之后，会再次调用Servlet的service方法

[TOC]


### StandardWrapperValue的主要作用

StandardWrapperValue是Wrapper的标准阀，用在Pipleline流程中的最后一个valve执行。当Request请求到达Context的时候，继续传递给Wrapper，首先是调用StandardWrapper的Invoke方法(ContainerBase父类中)，如下.

```java
public void invoke(Request request, Response response)
        throws IOException, ServletException {
        pipeline.invoke(request, response);
    }
```

pipeline就是与容器关联的一系列处理流程，StandardWrapperValve在初始化的时候就被加入到了pipeline中。而下面调用的pipeline的invoke方法，就是调用里面的各种Valve的invoke方法。

下面就是是StandedWrapperValve的核心逻辑，invoke方法：


```java
public void invoke(Request request, Response response,
                       ValveContext valveContext)
        throws IOException, ServletException {
        // Initialize local variables we may need
        boolean unavailable = false;
        Throwable throwable = null;
        StandardWrapper wrapper = (StandardWrapper) getContainer();
        ServletRequest sreq = request.getRequest();
        ServletResponse sres = response.getResponse();
        Servlet servlet = null;
        HttpServletRequest hreq = null;
        if (sreq instanceof HttpServletRequest)
            hreq = (HttpServletRequest) sreq;
        HttpServletResponse hres = null;
        if (sres instanceof HttpServletResponse)
            hres = (HttpServletResponse) sres;

        // Check for the application being marked unavailable
        if (!((Context) wrapper.getParent()).getAvailable()) {
            //503
            hres.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE,
                           sm.getString("standardContext.isUnavailable"));
            unavailable = true;
        }

        // Check for the servlet being marked unavailable
        if (!unavailable && wrapper.isUnavailable()) {
            log(sm.getString("standardWrapper.isUnavailable",
                             wrapper.getName()));
            if (hres == null) {
                ;       // NOTE - Not much we can do generically
            } else {
                long available = wrapper.getAvailable();
                if ((available > 0L) && (available < Long.MAX_VALUE))
                    hres.setDateHeader("Retry-After", available);
                hres.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE,
                               sm.getString("standardWrapper.isUnavailable",
                                            wrapper.getName()));
            }
            unavailable = true;
        }

        // Allocate a servlet instance to process this request
        try {
            if (!unavailable) {
                //通过Wrapper获取Servlet实例，内部已经调用了service(request, response)方法，对req与res进行了字段赋值
                //下面是对Response和Request进行后续的处理
                servlet = wrapper.allocate();
            }
        } catch (ServletException e) {
            log(sm.getString("standardWrapper.allocateException",
                             wrapper.getName()), e);
            throwable = e;
            exception(request, response, e);
            servlet = null;
        } catch (Throwable e) {
            log(sm.getString("standardWrapper.allocateException",
                             wrapper.getName()), e);
            throwable = e;
            exception(request, response, e);
            servlet = null;
        }

        // Acknowlege the request
        try {
            response.sendAcknowledgement();
        } catch (IOException e) {
            sreq.removeAttribute(Globals.JSP_FILE_ATTR);
            log(sm.getString("standardWrapper.acknowledgeException",
                             wrapper.getName()), e);
            throwable = e;
            exception(request, response, e);
        } catch (Throwable e) {
            log(sm.getString("standardWrapper.acknowledgeException",
                             wrapper.getName()), e);
            throwable = e;
            exception(request, response, e);
            servlet = null;
        }

        // Create the filter chain for this request
        // 创建对于Request与Response的过滤链
        ApplicationFilterChain filterChain =
            createFilterChain(request, servlet);

        // Call the filter chain for this request
        // NOTE: This also calls the servlet's service() method
        try {
            String jspFile = wrapper.getJspFile();
            if (jspFile != null)
                sreq.setAttribute(Globals.JSP_FILE_ATTR, jspFile);
            else
                sreq.removeAttribute(Globals.JSP_FILE_ATTR);
            if ((servlet != null) && (filterChain != null)) {
                //使用过滤链过滤Request与Response
                filterChain.doFilter(sreq, sres);
            }
            sreq.removeAttribute(Globals.JSP_FILE_ATTR);
        } catch (IOException e) {
            sreq.removeAttribute(Globals.JSP_FILE_ATTR);
            log(sm.getString("standardWrapper.serviceException",
                             wrapper.getName()), e);
            throwable = e;
            exception(request, response, e);
        } catch (UnavailableException e) {
            sreq.removeAttribute(Globals.JSP_FILE_ATTR);
            log(sm.getString("standardWrapper.serviceException",
                             wrapper.getName()), e);
            wrapper.unavailable(e);
            long available = wrapper.getAvailable();
            if ((available > 0L) && (available < Long.MAX_VALUE))
                hres.setDateHeader("Retry-After", available);
            hres.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE,
                           sm.getString("standardWrapper.isUnavailable",
                                        wrapper.getName()));
            // Do not save exception in 'throwable', because we
            // do not want to do exception(request, response, e) processing
        } catch (ServletException e) {
            sreq.removeAttribute(Globals.JSP_FILE_ATTR);
            log(sm.getString("standardWrapper.serviceException",
                             wrapper.getName()), e);
            throwable = e;
            exception(request, response, e);
        } catch (Throwable e) {
            sreq.removeAttribute(Globals.JSP_FILE_ATTR);
            log(sm.getString("standardWrapper.serviceException",
                             wrapper.getName()), e);
            throwable = e;
            exception(request, response, e);
        }

        // Release the filter chain (if any) for this request
        try {
            if (filterChain != null)
                //释放过滤链
                filterChain.release();
        } catch (Throwable e) {
            log(sm.getString("standardWrapper.releaseFilters",
                             wrapper.getName()), e);
            if (throwable == null) {
                throwable = e;
                exception(request, response, e);
            }
        }

        // Deallocate the allocated servlet instance
        try {
            if (servlet != null) {
                //释放servlet
                wrapper.deallocate(servlet);
            }
        } catch (Throwable e) {
            log(sm.getString("standardWrapper.deallocateException",
                             wrapper.getName()), e);
            if (throwable == null) {
                throwable = e;
                exception(request, response, e);
            }
        }

        // If this servlet has been marked permanently unavailable,
        // unload it and release this instance
        try {
            if ((servlet != null) &&
                (wrapper.getAvailable() == Long.MAX_VALUE)) {
                //回收所有的Servlet
                wrapper.unload();
            }
        } catch (Throwable e) {
            log(sm.getString("standardWrapper.unloadException",
                             wrapper.getName()), e);
            if (throwable == null) {
                throwable = e;
                exception(request, response, e);
            }
        }

    }
```

上面的代码看着挺长的，对照代码主要做了下面几件事：
1. 通过Wrapper获取Servlet实例，内部已经调用了service(request, response)方法，对req与res进行了字段赋值
2. 给请求发送确认，这时还是空实现
3. 创建对于Request与Response的过滤链
4. 使用过滤链过滤Request与Response
5. 释放servlet，释放的STM类型的servlet会被放入的stack中，以后会被再次使用
6. 根据情况决定是否回收所有的Servlet



#### 1. 就是通过Wrapper加载Servlet，并且调用了Servlet的Service方法。

这里的的Wrapper是指Wrapper的标准实现：StandardWrapper。里面的allocate方法会通过ClassLoader来加载一个Servlet。
具体的逻辑看[StandWrapper]()

#### 2. 给请求发送确认，这时还是空实现

Send an acknowledgment of a request.发送一个请求的确认。在tomcat4版本还是空实现

#### 3. 创建对于Request与Response的过滤链

过滤链的过滤项是在Wrapper的父容器Context(StanderContext)中设置的。StanderContext提供了FilterMap[]数组来存储各个过滤项。Filter使用户可以改变一个request和修改一个response，它不是一个servlet，也不能产生response，它能够在一个request到达servlet之前预处理request，也可以在response离开servlet时处理response。

[How Tomcat Works - Chapter 11 - Standard Wrapper](http://www.ciaoshen.com/web/java/how%20tomcat%20works/2017/11/12/how-tomcat-works-chapter-eleven-standard-wrapper-context.html)
[Tomcat Filter 源码分析](https://www.jianshu.com/p/be47c9d89175)
```
private FilterMap filterMaps[] = new FilterMap[0];
```
那么FilterMap到底是什么呢？下面是FilterMap的定义,里面只包含了是三个字段：
```java
private String filterName = null;		//当成功匹配时，执行名字为filterName的筛选器
private String servletName = null;		//用于匹配Request的name,对应的StandardWrapper设置的Name
private String urlPattern = null;		//用于匹配Request中的RequestURL，最终要对应一个StanderWrapper
```

而在StanderWrapperValue中，通过父容器StanderderContext获取到这个FielterMap[]数组，通过下面的代码构建过滤链：

```java
private ApplicationFilterChain createFilterChain(Request request,
                                                     Servlet servlet) {

        if (servlet == null)
            return (null);

        // 创建并且初始化一个过滤链对象ApplicationFilterChain
        ApplicationFilterChain filterChain = new ApplicationFilterChain();
        filterChain.setServlet(servlet);

        StandardWrapper wrapper = (StandardWrapper) getContainer();
        filterChain.setSupport(wrapper.getInstanceSupport());

        // 获取Context的过滤mappings
        StandardContext context = (StandardContext) wrapper.getParent();
        FilterMap filterMaps[] = context.findFilterMaps();

        if ((filterMaps == null) || (filterMaps.length == 0))
            return (filterChain);

        // 获取我们需要的匹配 fliter mappings 的信息
        String requestPath = null;
        if (request instanceof HttpRequest) {
            HttpServletRequest hreq = (HttpServletRequest) request.getRequest();
            String contextPath = hreq.getContextPath(); //设置的一个context对用的mapper匹配路径
            if (contextPath == null) {
                contextPath = "";
            }
            String requestURI = ((HttpRequest) request).getDecodedRequestURI();
            //去掉RequestURI中的contextPath
            if (requestURI.length() >= contextPath.length()) {
                requestPath = requestURI.substring(contextPath.length());
            }
        }

        String servletName = wrapper.getName();
        int n = 0;

        // 根据RequestURI匹配FilterMaps中的过滤项，添加到filterChain中
        for (int i = 0; i < filterMaps.length; i++) {
            if (!matchFiltersURL(filterMaps[i], requestPath))
                continue;
            ApplicationFilterConfig filterConfig = (ApplicationFilterConfig) context.findFilterConfig(filterMaps[i].getFilterName());
            if (filterConfig == null) {
                continue;
            }
            filterChain.addFilter(filterConfig);
            n++;
        }

        // 根据StanderWrapper的Name来匹配FilterMaps中的过滤项，添加到filterChain
        for (int i = 0; i < filterMaps.length; i++) {
            if (!matchFiltersServlet(filterMaps[i], servletName))
                continue;
            ApplicationFilterConfig filterConfig = (ApplicationFilterConfig) context.findFilterConfig(filterMaps[i].getFilterName());
            if (filterConfig == null) {
                continue;
            }
            filterChain.addFilter(filterConfig);
            n++;
        }

        return (filterChain);
    }
```

在 createFilterChain 的过程中，调用了matchFiltersURL()，matchFiltersServlet()方法来判断FliterMap是否匹配到当前Request

* matchFiltersServlet：比较匹配ServletName

``` java
    private boolean matchFiltersServlet(FilterMap filterMap,String servletName) {

        if (servletName == null)
            return (false);
        else {
            //比较的是FilterMap的ServletName与StanderWrapper的Name(即Servlet)
            return (servletName.equals(filterMap.getServletName()));
          }
    }
```

* 调用了matchFiltersURL：比较匹配URL。解释在注释里面

``` java
private boolean matchFiltersURL(FilterMap filterMap, String requestPath) {

        if (requestPath == null)
            return (false);

        String testPath = filterMap.getURLPattern();
        if (testPath == null)
            return (false);

        // Case 1 - Exact Match 完全匹配
        if (testPath.equals(requestPath))
            return (true);

        // Case 2 - Path Match ("/.../*")
        if (testPath.equals("/*"))
            return (true);
        if (testPath.endsWith("/*")) {
            String comparePath = requestPath;
            //前缀匹配，如：匹配a/, /a/b/c:匹配顺序就是a/b/c --> /a/b --> a/ -->true
            while (true) {
                if (testPath.equals(comparePath + "/*"))
                    return (true);
                int slash = comparePath.lastIndexOf('/');
                if (slash < 0)
                    break;
                comparePath = comparePath.substring(0, slash);
            }
            return (false);
        }

        // Case 3 - Extension Match 后缀匹配RequestURL '.' 出现之后的部分
        if (testPath.startsWith("*.")) {
            int slash = requestPath.lastIndexOf('/');
            int period = requestPath.lastIndexOf('.');
            if ((slash >= 0) && (period > slash))
                return (testPath.equals("*." + requestPath.substring(period + 1)));
        }

        // Case 4 - "Default" Match
        return (false); // NOTE - Not relevant for selecting filters
    }
```

#### 4. 使用过滤链过滤Request与Response：filterChain.doFilter(sreq, sres);

这里是调用ApplicationFilterChain的doFilter()方法，doFilter内部有调用了 internalDoFilter()方法。下面是internalDoFilter方法的实现：

```java
private void internalDoFilter(ServletRequest request, ServletResponse response)
        throws IOException, ServletException {

        if (this.iterator == null)
            this.iterator = filters.iterator();

        //调用每一个Filter的Filter方法
        if (this.iterator.hasNext()) {
            ApplicationFilterConfig filterConfig =
              (ApplicationFilterConfig) iterator.next();
            Filter filter = null;
            try {
                filter = filterConfig.getFilter();
                support.fireInstanceEvent(InstanceEvent.BEFORE_FILTER_EVENT,
                                          filter, request, response);
                //调用过滤方法
                filter.doFilter(request, response, this);
                support.fireInstanceEvent(InstanceEvent.AFTER_FILTER_EVENT,
                                          filter, request, response);
            } 
            return;
        }

        try {
            support.fireInstanceEvent(InstanceEvent.BEFORE_SERVICE_EVENT,
                                      servlet, request, response);
            if ((request instanceof HttpServletRequest) &&
                (response instanceof HttpServletResponse)) {
                servlet.service((HttpServletRequest) request,
                                (HttpServletResponse) response);
            } else {
                //如果存在Filter，会再次调用service方法处理request
                servlet.service(request, response);
            }
            support.fireInstanceEvent(InstanceEvent.AFTER_SERVICE_EVENT,
                                      servlet, request, response);
        }

    }
```
上面方法就只做了两件事：
1. 调用每一个Filter的doFilter方法
2. 在调用每一个Filter完成之后，再次调用service方法处理request。这里已经调用Service方法两次了。通过StanderWrapper获取Servlet的时候，完成Servlet加载之后，就已经调用了一次Service方法。

doFilter的之后的逻辑，没有看，就不说了。

#### 5. 释放servlet，释放的STM类型的servlet会被放入的stack中，以后会被再次使用：deallocate(Servlet servlet)

dealoocate的实现是在StanderWrapper中，在servlet完成service方法，完成对于request，response处理之后，之后就调用这个方法，完成回收工作

```java
public void deallocate(Servlet servlet) throws ServletException {

        //如果不是STM，就直接返回。STM类型的Servlet每次只有一个Servelt在执行Service方法
        if (!singleThreadModel) {
            countAllocated--;
            return;
        }

        // 如果是STM，就放入到实例池中，以备后来使用
        synchronized (instancePool) {
            countAllocated--;
            instancePool.push(servlet);
            instancePool.notify();
        }
    }
```