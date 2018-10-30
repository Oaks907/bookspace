[TOC]



## Context

一般来说，一个Context就代表在Tomcat webapp目录中的一个项目

## StandardContext

StandardContext是Context的标准实现，它里面包含了Context具有的各种功能，外接通过获取到该对象，来获取到支持。

### StandardContext具有的功能

* 对外提供各种方法，通过对象实例提供方法
* 当Host中的标准阀StandardHostValve一旦匹配到合适的Context，就会使用该Context调用里面的invoke方法继续处理Reqeust，将请求处理由Host父容器，传递到下层子容器Context

### StandardContext包含的嵌组件

* 

### StandardContext的类图

![](https://upload-images.jianshu.io/upload_images/1916953-09cf407f182b31e2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### StandardContext的参数

### StandardContext的构造函数

```java
 public StandardContext() {
        super();
        //StandardContextValve基础阀，会处理从连接器中接收到的每个HTTP请求
        pipeline.setBasic(new StandardContextValve());
        namingResources.setContainer(this);
    }
```

## StandardContextValve

StandardContextValve是Context的基础阀。

包含的功能：
* StandardContextValve负责通过关联的容器Context中的map()方法来查找符合要求的Wrapper。
* 使用查找到的Wrapper去处理当前的Request

核心代码：

```java
public void invoke(Request request, Response response, ValveContext valveContext) throws IOException, ServletException {

        // 校验类型
        if (!(request.getRequest() instanceof HttpServletRequest) ||
            !(response.getResponse() instanceof HttpServletResponse)) {
            return;     
        }

        //禁止访问路径的URI中存在 WEB-INF or META-INF
        HttpServletRequest hreq = (HttpServletRequest) request.getRequest();
        String contextPath = hreq.getContextPath();
        String requestURI = ((HttpRequest) request).getDecodedRequestURI();
        String relativeURI = requestURI.substring(contextPath.length()).toUpperCase();
        if (relativeURI.equals("/META-INF") ||
            relativeURI.equals("/WEB-INF") ||
            relativeURI.startsWith("/META-INF/") ||
            relativeURI.startsWith("/WEB-INF/")) {
            notFound(requestURI, (HttpServletResponse) response.getResponse());
            return;
        }

        //获取该Valve关联的容器
        Context context = (Context) getContainer();

        // 选择处理该请求的Mapper
        Wrapper wrapper = null;
        try {
            //通过map方法去context中匹配合适的request
            wrapper = (Wrapper) context.map(request, true);
        } catch (IllegalArgumentException e) {
            badRequest(requestURI, (HttpServletResponse) response.getResponse());
            return;
        }
        if (wrapper == null) {
            notFound(requestURI, (HttpServletResponse) response.getResponse());
            return;
        }

        response.setContext(context);
        //使用查找到的wrapper处理该request
        wrapper.invoke(request, response);
    }
```


## StandardContextMapper

StandardContextMapper是Tomcat用来通过访问的URL路径来匹配合适的Wrapper(即Servlet)。它实现了Mapper类的map接口。

匹配规则如下：
假如请求为：http://localhost:8081/app1/ModernServlet
 设置的Context路径为：contextPath='/app1' 
 请求路径为：requestURI='/app1/ModernServlet' 
 相对路径为：relativeURI='/ModernServlet'

1. 使用相对路径进行精确匹配，匹配不成功到 2
2. 使用相对路径进行前缀匹配：/ModernServle/* ---> /*。匹配不成功到 3
3. 使用相对路径进行扩展匹配：*.jsp 等
4. 如果设置了默认匹配，默认匹配／



StandardContextMapper是帮助Context查找到特定Wrapper使用的适配类，它实现了Mapper接口，核心方法是map(Request request, boolean update)

```java
public Container map(Request request, boolean update) {

        int debug = context.getDebug();

        // 判断是否已经被重新匹配
        if (update && (request.getWrapper() != null))
            return (request.getWrapper());

        // Identify the context-relative URI to be mapped
        String contextPath = ((HttpServletRequest) request.getRequest()).getContextPath();
        String requestURI = ((HttpRequest) request).getDecodedRequestURI();
        String relativeURI = requestURI.substring(contextPath.length());

        // Mapping contextPath='/app1' with requestURI='/app1/ModernServlet' and relativeURI='/ModernServlet'
        if (debug >= 1)
            context.log("Mapping contextPath='" + contextPath +
                        "' with requestURI='" + requestURI +
                        "' and relativeURI='" + relativeURI + "'");

        Wrapper wrapper = null;
        String servletPath = relativeURI;
        String pathInfo = null;
        String name = null;

        // 第一次：精确匹配,找到对应的Wrapper，即Servelt
        if (wrapper == null) {
            if (debug >= 2)
                context.log("  Trying exact match");
            if (!(relativeURI.equals("/")))
                name = context.findServletMapping(relativeURI);
            if (name != null)
                wrapper = (Wrapper) context.findChild(name);
            if (wrapper != null) {
                servletPath = relativeURI;
                pathInfo = null;
            }
        }

        //第二次：前缀匹配
        if (wrapper == null) {
            if (debug >= 2)
                context.log("  Trying prefix match");
            servletPath = relativeURI;
            while (true) {
                name = context.findServletMapping(servletPath + "/*");
                if (name != null)
                    wrapper = (Wrapper) context.findChild(name);
                if (wrapper != null) {
                    pathInfo = relativeURI.substring(servletPath.length());
                    if (pathInfo.length() == 0)
                        pathInfo = null;
                    break;
                }
                int slash = servletPath.lastIndexOf('/');   //最后一个'/'的索引
                if (slash < 0)
                    break;
                servletPath = servletPath.substring(0, slash);  //获取0到slash的字符串
            }
        }

        //第三次：扩展名匹配 *. 开头的处理
        if (wrapper == null) {
            if (debug >= 2)
                context.log("  Trying extension match");
            int slash = relativeURI.lastIndexOf('/');
            if (slash >= 0) {
                String last = relativeURI.substring(slash); //获取slash位置索引到最后的字符串
                int period = last.lastIndexOf('.');
                if (period >= 0) {
                    String pattern = "*" + last.substring(period);  //获取period到最后的字符串
                    name = context.findServletMapping(pattern);
                    if (name != null)
                        wrapper = (Wrapper) context.findChild(name);
                    if (wrapper != null) {
                        servletPath = relativeURI;
                        pathInfo = null;
                    }
                }
            }
        }

        //第四次：默认匹配"/"
        if (wrapper == null) {
            if (debug >= 2)
                context.log("  Trying default match");
            name = context.findServletMapping("/");     
            if (name != null)
                wrapper = (Wrapper) context.findChild(name);
            if (wrapper != null) {
                servletPath = relativeURI;
                pathInfo = null;
            }
        }

        if ((debug >= 1) && (wrapper != null))
            context.log(" Mapped to servlet '" + wrapper.getName() +
                        "' with servlet path '" + servletPath +
                        "' and path info '" + pathInfo +
                        "' and update=" + update);
        if (update) {
            request.setWrapper(wrapper);
            ((HttpRequest) request).setServletPath(servletPath);
            ((HttpRequest) request).setPathInfo(pathInfo);
        }
        return (wrapper);

    }
```



·参考：

1. 《深入剖析Tomcat》