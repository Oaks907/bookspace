[TOC]

## Wrapper
wrapper是一个容器，它代表Web应用程序的部署描述符中的单个servlet定义。请求会通过Wrapper的具体实现StandardWrapper来加载一个Servlet，并且调用里面的方法处理请求。

下面看下，一个Wrapper需要具有哪些功能：

里面的方法没有在这里做说明。详细的向下看StandardWrapper中有解释
```java
public interface Wrapper extends Container {
    public long getAvailable();
    public void setAvailable(long available);
    public String getJspFile();
    public void setJspFile(String jspFile);
    public int getLoadOnStartup();
    public void setLoadOnStartup(int value);
    public String getRunAs();
    public void setRunAs(String runAs);
    public String getServletClass();
    public void setServletClass(String servletClass);
    public boolean isUnavailable();
    public void addInitParameter(String name, String value);
    public void addInstanceListener(InstanceListener listener);
    public void addSecurityReference(String name, String link);
    public Servlet allocate() throws ServletException;
    public void deallocate(Servlet servlet) throws ServletException;
    public String findInitParameter(String name);
    public String[] findInitParameters();
    public String findSecurityReference(String name);
    public String[] findSecurityReferences();
    public void load() throws ServletException;
    public void removeInitParameter(String name);
    public void removeInstanceListener(InstanceListener listener);
    public void removeSecurityReference(String name);
    public void unavailable(UnavailableException unavailable);
    public void unload() throws ServletException;
}
```


## StandardWrapper

StandardWrapper的主要任务是载入它所代表的的servlet，并进行实例化。但是，standardWrapper并不直接调用Servlet的Service方法。这个工作是StandardWrapperValve对象来完成的。

### 1. StandardWrapper的方法调用图

![图片.png](https://upload-images.jianshu.io/upload_images/1916953-c2043c5595caf783.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 2. StandardWrapper的类关系图

![图片.png](https://upload-images.jianshu.io/upload_images/1916953-5b27a44b43aa4128.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 3. StandardWrapper的参数结构

![图片.png](https://upload-images.jianshu.io/upload_images/1916953-01eae2ef8cfe1f22.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```java
private long available = 0L;        //该servlet是否可用的时间。为0代表有效，MAX_VALUE:永久不可用
private int countAllocated = 0;     //当前正在分配的活动总数
private int debug = 0;              //debug等级
private StandardWrapperFacade facade = new StandardWrapperFacade(this); //Servlet的外观类，用于Servlet的参数初始化
private Servlet instance = null;            //servlet实例
private InstanceSupport instanceSupport = new InstanceSupport(this); //该Wrapper的事件监听相关
private String jspFile = null;              // 如果是JSP类型的Wraper。代表这个servlet的JSP文件的上下文相对URI。
private int loadOnStartup = -1;             // servlet的启动顺序。负数代表第一次加载
private HashMap parameters = new HashMap(); //为Servelt设置的初始化参数
private HashMap references = new HashMap(); //该servlet的安全角色引用，由servlet中使用的role name 为key。
private String runAs = null;                //该Servlet运行时的身份
private String servletClass = null;         //要加载的servlet的名字
private boolean singleThreadModel = false;  //是否是singleThreadModel类型的Wrapper(servlet)
private boolean unloading = false;          //是否加载servlet
private int maxInstances = 20;              //STM类型的servlet最大数量
private int nInstances = 0;                 //STM类型的servlet当前存在的数量
private Stack instancePool = null;          //存储STM的Stack
```

### 4. StandardWrapper提供的方法

#### 4.1 实现的Wrapper相关的方法

* void start()
start方法，StandardWrapper的start方法，使用的是父类方法的start方法，里面依次调用了各个构件与子容器。
```java
public void start() throws LifecycleException {
        super.start();
    }
```

* void stop()
同样是调用了父类的方法。在这之前判断了servlet是否已经初始化了

```java
public void stop() throws LifecycleException {
        //如果servlet已经初始化了。unload方法，见下面
        try {
            unload();
        } catch (ServletException e) {
            log(sm.getString("standardWrapper.unloadException", getName()), e);
        }
        // 调用父类的Stop方法，停止各个构件与子容器
        super.stop();
    }
```

* void addChild(Container child)
因为Wrapper是四大组件容器的最后一个，不能继续添加子容器了。调用该方法会抛出异常：

```java
 public void addChild(Container child) {
        throw new IllegalStateException (sm.getString("standardWrapper.notChild"));
    }
```


下面是为Servlet提供的初始化参数。使用HashMap存储了起来：
* void addInitParameter(String name, String value)
* removeInitParameter(String name)
* String getInitParameter(String name)
* String findInitParameter(String name)
* String[] findInitParameters()

```java
public void addInitParameter(String name, String value) {
        synchronized (parameters) {
            parameters.put(name, value);
        }
        fireContainerEvent("addInitParameter", name);
    }

public void removeInitParameter(String name) {
        synchronized (parameters) {
            parameters.remove(name);
        }
        fireContainerEvent("removeInitParameter", name);
    }
public String getInitParameter(String name) {
        return (findInitParameter(name));
    }
public String findInitParameter(String name) {
        synchronized (parameters) {
            return ((String) parameters.get(name));
        }
    }
public String[] findInitParameters() {
        synchronized (parameters) {
            String results[] = new String[parameters.size()];
            return ((String[]) parameters.keySet().toArray(results));
        }

    }
```

通过InstanceSupport，来实现对于Instance的监听。触发相关的Event的时候，通知订阅的观察者Listener。在下面补充了使用到了的，InstanceSupport的部分方法

* addInstanceListener(InstanceListener listener)
* void removeInstanceListener(InstanceListener listener)

```java
public void removeInstanceListener(InstanceListener listener) {
        instanceSupport.removeInstanceListener(listener);
    }
public void addInstanceListener(InstanceListener listener) {
        instanceSupport.addInstanceListener(listener);
    }
```


这个具体的作用没有弄明白，解释是：
该servlet的安全角色引用，由servlet中使用的角色名键入。相应的值是Web应用程序本身的角色名。
实现还是比较简单的
* void addSecurityReference(String name, String link)
* void removeSecurityReference(String name)
* String findSecurityReference(String name)
* String[] findSecurityReferences()

```java
public void addSecurityReference(String name, String link) {
        synchronized (references) {
            references.put(name, link);
        }
        fireContainerEvent("addSecurityReference", name);
    }

public void removeInitParameter(String name) {
        synchronized (parameters) {
            parameters.remove(name);
        }
        fireContainerEvent("removeInitParameter", name);
    }

public String findSecurityReference(String name) {
        synchronized (references) {
            return ((String) references.get(name));
        }
    }

public String[] findSecurityReferences() {
        synchronized (references) {
            String results[] = new String[references.size()];
            return ((String[]) references.keySet().toArray(results));
        }
    }
```

下面是Servlet加载的相关内容：
* void load() 
```java
public synchronized void load() throws ServletException {
        instance = loadServlet();
    }
```
* Servlet loadServlet() 

loaderServlet概括起来只做了两件事：1. 加载Servlert 2.对于jsp类型的servlet特殊处理
```java
public synchronized Servlet loadServlet() throws ServletException {

        //非STM且instance不为null
        if (!singleThreadModel && (instance != null))
            return instance;

        //获取System.out与System.err的输出，便于它使用ServletContext的log()方法记录消息日志
        PrintStream out = System.out;
        SystemLogHandler.startCapture();
        Servlet servlet = null;
        try {
            // 检查请求的servlet是不是一个JSP界面。若是，loadServlet()方法需要获取代表该JSP页面的实际Servlet类
            String actualClass = servletClass;
            if ((actualClass == null) && (jspFile != null)) {
                //由于Catalina也是一个JSP界面，因此必须检查请求是否是不是一个JSP界面。若是loadServlet方法需要该JSP界面的实际的servlet类。
                Wrapper jspWrapper = (Wrapper) ((Context) getParent()).findChild(Constants.JSP_SERVLET_NAME);
                if (jspWrapper != null) {
                  actualClass = jspWrapper.getServletClass();
                }
            }

            // actualClass没有指定
            if (actualClass == null) {
                unavailable(null);
                throw new ServletException(sm.getString("standardWrapper.notClass", getName()));
            }

            // 获取载入器
            Loader loader = getLoader();
            if (loader == null) {
                unavailable(null);
                throw new ServletException(sm.getString("standardWrapper.missingLoader", getName()));
            }

            //获取类加载器
            ClassLoader classLoader = loader.getClassLoader();

            // Special case class loader for a container provided servlet
            if (isContainerProvidedServlet(actualClass)) {
                classLoader = this.getClass().getClassLoader();
                log(sm.getString("standardWrapper.containerServlet", getName()));
            }

            //加载指定的 servlet class
            Class classClass = null;
            try {
                if (classLoader != null) {
                    System.out.println("Using classLoader.loadClass");
                    classClass = classLoader.loadClass(actualClass);
                } else {
                    System.out.println("Using forName");
                    classClass = Class.forName(actualClass);
                }
            } catch (ClassNotFoundException e) {
                unavailable(null);
                throw new ServletException(sm.getString("standardWrapper.missingClass", actualClass), e);
            }
            if (classClass == null) {
                unavailable(null);
                throw new ServletException(sm.getString("standardWrapper.missingClass", actualClass));
            }

           //实例化并初始化servlet类本身的实例
            try {
                servlet = (Servlet) classClass.newInstance();
            } catch (ClassCastException e) {
                unavailable(null);
                throw new ServletException(sm.getString("standardWrapper.notServlet", actualClass), e);
            } catch (Throwable e) {
                unavailable(null);
                throw new ServletException(sm.getString("standardWrapper.instantiate", actualClass), e);
            }

           //检查是否允许在这个Web应用程序中加载servlet
            if (!isServletAllowed(servlet)) {
                throw new SecurityException
                    (sm.getString("standardWrapper.privilegedServlet",
                                  actualClass));
            }

            // 如果servlet类是一个ContainerServlet。调用setWrapper，传入StandardWrapper
            if ((servlet instanceof ContainerServlet) &&
                isContainerProvidedServlet(actualClass)) {
System.out.println("calling setWrapper");                  
                ((ContainerServlet) servlet).setWrapper(this);
System.out.println("after calling setWrapper");                  
            }


            // 调用servler的初始化方法
            try {
                instanceSupport.fireInstanceEvent(InstanceEvent.BEFORE_INIT_EVENT, servlet);

                //借助StandardWrapperFacade传入ServltConfig，里面包含了在Wrapper中设置的参数parameters
                servlet.init(facade);
                // 对于JSP特殊处理
                if ((loadOnStartup > 0) && (jspFile != null)) {
                    // Invoking jspInit
                    HttpRequestBase req = new HttpRequestBase();
                    HttpResponseBase res = new HttpResponseBase();
                    req.setServletPath(jspFile);
                    req.setQueryString("jsp_precompile=true");
                    //调用Servlet的service方法
                    servlet.service(req, res);
                }
                instanceSupport.fireInstanceEvent(InstanceEvent.AFTER_INIT_EVENT,
                                                  servlet);
            } catch (UnavailableException f) {
                instanceSupport.fireInstanceEvent(InstanceEvent.AFTER_INIT_EVENT,
                                                  servlet, f);
                unavailable(f);
                throw f;
            } catch (ServletException f) {
                instanceSupport.fireInstanceEvent(InstanceEvent.AFTER_INIT_EVENT,
                                                  servlet, f);
                throw f;
            } catch (Throwable f) {
                instanceSupport.fireInstanceEvent(InstanceEvent.AFTER_INIT_EVENT,
                                                  servlet, f);
                throw new ServletException
                    (sm.getString("standardWrapper.initException", getName()), f);
            }

            //如果servlet是STM，初始化instancePoo
            singleThreadModel = servlet instanceof SingleThreadModel;
            if (singleThreadModel) {
                if (instancePool == null)
                    instancePool = new Stack();
            }
            fireContainerEvent("load", this);
        } finally {
            String log = SystemLogHandler.stopCapture();
            if (log != null && log.length() > 0) {
                if (getServletContext() != null) {
                    getServletContext().log(log);
                } else {
                    out.println(log);
                }
            }
        }
        return servlet;

    }
```
* void unload()

主要是调用Servlet的destory方法；保存的Context的老的classLoader；重置了一些状态位；对于STM类型的Servlet进行了特殊处理
```java
public synchronized void unload() throws ServletException {

        if (!singleThreadModel && (instance == null))
            return;
        unloading = true;

        // 如果当前实例已经被分配，这里暂停一下
        if (countAllocated > 0) {
            int nRetries = 0;
            while (nRetries < 10) {
                if (nRetries == 0) {
                    log("Waiting for " + countAllocated + " instance(s) to be deallocated");
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    
                }
                nRetries++;
            }
        }

        ClassLoader oldCtxClassLoader =
            Thread.currentThread().getContextClassLoader();
        ClassLoader classLoader = instance.getClass().getClassLoader();

        //日志处理，开始捕获日志
        PrintStream out = System.out;
        SystemLogHandler.startCapture();

        //调用servlet的destroy方法
        try {
            instanceSupport.fireInstanceEvent
              (InstanceEvent.BEFORE_DESTROY_EVENT, instance);
            Thread.currentThread().setContextClassLoader(classLoader);
            instance.destroy();
            instanceSupport.fireInstanceEvent(InstanceEvent.AFTER_DESTROY_EVENT, instance);
        } catch (Throwable t) {
            instanceSupport.fireInstanceEvent
              (InstanceEvent.AFTER_DESTROY_EVENT, instance, t);
            instance = null;
            instancePool = null;
            nInstances = 0;
            fireContainerEvent("unload", this);
            unloading = false;
            throw new ServletException
                (sm.getString("standardWrapper.destroyException", getName()),
                 t);
        } finally {
            // 存储context的classLoader
            Thread.currentThread().setContextClassLoader(oldCtxClassLoader);
            // 写入捕获的输出
            String log = SystemLogHandler.stopCapture();
            if (log != null && log.length() > 0) {
                if (getServletContext() != null) {
                    getServletContext().log(log);
                } else {
                    out.println(log);
                }
            }
        }

        // 取消被destory的instance
        instance = null;

        // 如果该Servlet是STM类型的，并且实例池不为null
        if (singleThreadModel && (instancePool != null)) {
            try {
                Thread.currentThread().setContextClassLoader(classLoader);
                //所有instance出stack
                while (!instancePool.isEmpty()) {
                    ((Servlet) instancePool.pop()).destroy();
                }
            } catch (Throwable t) {
                instancePool = null;
                nInstances = 0;
                unloading = false;
                fireContainerEvent("unload", this);
                throw new ServletException
                    (sm.getString("standardWrapper.destroyException",
                                  getName()), t);
            } finally {
                // 存储context的ClassLoader
                Thread.currentThread().setContextClassLoader
                    (oldCtxClassLoader);
            }
            instancePool = null;
            nInstances = 0;
        }
        //重置servlet为不在加载状态
        unloading = false;
        fireContainerEvent("unload", this);
    }
```
* Servlet allocate()：分配Servlet，没有会调用loaderServlet加载。STM的特殊处理
```java
public Servlet allocate() throws ServletException {

        if (debug >= 1)
            log("Allocating an instance");

        //如果正在卸载 servlet，抛出异常
        if (unloading)
            throw new ServletException(sm.getString("standardWrapper.unloading", getName()));

        //SingleThreadModel保证servlet中的service方法不会同时被两个线程执行。
        //如果不是STM类型的Servlet，每次返回相同的实例。
        //因为每次在HttpConnect的Processor中对于每个请求会分配一个线程。实际这里还是只有一个实例，只不过会被重复利用
        if (!singleThreadModel) {

            // 如果instance还没有被初始化。这里重新初始化一下
            if (instance == null) {
                synchronized (this) {
                    if (instance == null) {
                        try {
                            //初始化servlet
                            instance = loadServlet();
                        } catch (ServletException e) {
                            throw e;
                        } catch (Throwable e) {
                            throw new ServletException
                                (sm.getString("standardWrapper.allocate"), e);
                        }
                    }
                }
            }

            if (!singleThreadModel) {
                if (debug >= 2)
                    log("  Returning non-STM instance");
                countAllocated++;
                return (instance);
            }
        }

        synchronized (instancePool) {
            //对于STM，如果分配的Servlet总数，大于已存在的实例数
            while (countAllocated >= nInstances) {
                // 小于最大实例数，就增加新的，大于就等待原来的实例
                if (nInstances < maxInstances) {
                    try {
                        //增加新的实例
                        instancePool.push(loadServlet());
                        nInstances++;
                    } catch (ServletException e) {
                        throw e;
                    } catch (Throwable e) {
                        throw new ServletException
                            (sm.getString("standardWrapper.allocate"), e);
                    }
                } else {
                    try {
                        instancePool.wait();
                    } catch (InterruptedException e) {
                        ;
                    }
                }
            }
            if (debug >= 2)
                log("  Returning allocated STM instance");
            countAllocated++;
            //取出栈顶的元素
            return (Servlet) instancePool.pop();
        }
    }
```
* void deallocate(Servlet servlet)

```java
public void deallocate(Servlet servlet) throws ServletException {
        //如果不是STM，就直接返回
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

* void unavailable(UnavailableException unavailable)：代表Servlet是否时候要有效。0代表有效，MAX_VALUE代表永久无效
``` java
public void unavailable(UnavailableException unavailable) {
        log(sm.getString("standardWrapper.unavailable", getName()));
        if (unavailable == null)
            setAvailable(Long.MAX_VALUE);
        else if (unavailable.isPermanent())
            setAvailable(Long.MAX_VALUE);
        else {
            int unavailableSeconds = unavailable.getUnavailableSeconds();
            if (unavailableSeconds <= 0)
                unavailableSeconds = 60;        // Arbitrary default
            setAvailable(System.currentTimeMillis() +
                         (unavailableSeconds * 1000L));
        }

    }
```




## InstanceSupport
InstanceSupport对于是StandardWrapper的辅助类，对于StanderWrapper提供观察模式的实现，能够添加Listener，当有Event到来的时候，通知订阅的各个Listener
* 订阅
```java
public void addInstanceListener(InstanceListener listener) {
      synchronized (listeners) {
          InstanceListener results[] =
            new InstanceListener[listeners.length + 1];
          for (int i = 0; i < listeners.length; i++)
              results[i] = listeners[i];
          results[listeners.length] = listener;
          listeners = results;
      }
    }
```
* 取消
```java
public void removeInstanceListener(InstanceListener listener) {
        synchronized (listeners) {
            int n = -1;
            for (int i = 0; i < listeners.length; i++) {
                if (listeners[i] == listener) {
                    n = i;
                    break;
                }
            }
            if (n < 0)
                return;
            InstanceListener results[] =
              new InstanceListener[listeners.length - 1];
            int j = 0;
            for (int i = 0; i < listeners.length; i++) {
                if (i != n)
                    results[j++] = listeners[i];
            }
            listeners = results;
        }

    }
```
* 事件触发
```java
public void fireInstanceEvent(String type, Filter filter,
                                  ServletRequest request,
                                  ServletResponse response) {

        if (listeners.length == 0)
            return;

        InstanceEvent event = new InstanceEvent(wrapper, filter, type, request, response);
        InstanceListener interested[] = null;
        synchronized (listeners) {
            interested = (InstanceListener[]) listeners.clone();
        }
        //触发事件
        for (int i = 0; i < interested.length; i++)
            interested[i].instanceEvent(event);
    }
```