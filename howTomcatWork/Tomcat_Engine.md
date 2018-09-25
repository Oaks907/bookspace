[TOC]



## Engine接口

* Engine是Tomcat的servlet引擎。当部署Tomcat要支持多个虚拟主机时，就需要使用Engine容器。
* Engine接收到Connector转交过来的请求时，会选择一个Host，将Request传递给子容器。

Engine的两个作用：

* 您希望使用Interceptor来查看由整个Engine处理的每一个请求。
* 您希望用独立的HTTP连接器运行Calalina，但仍希望支持多个虚拟主机Host。


下面是Engine的具有的接口：

```java
public interface Engine extends Container {
    // ------------------------------------------------------------- Properties
    //default Host
    public String getDefaultHost();
    public void setDefaultHost(String defaultHost);

    //唯一，在Cluster中生成Session的时候会使用到。具体见下面链接
    public String getJvmRoute();
    public void setJvmRoute(String jvmRouteId);
    
    //Service
    public Service getService();
    public void setService(Service service);
    
    //default context
    public void addDefaultContext(DefaultContext defaultContext);
    public DefaultContext getDefaultContext();

    // --------------------------------------------------------- Public Methods
    // 用于保存Host创建Context使用的默认配置
    public void importDefaultContext(Context context);
}
```

PS：
[Engine中defaultHost与JvmRoute](http://www.10tiao.com/html/308/201702/2650076429/1.html)

## Engine标准实现StandardEngine

### StandardEngine类关系图

![](https://upload-images.jianshu.io/upload_images/1916953-a56f5cd735da9ced.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

###  StandardEngine的参数

```java
private static final String info = "org.apache.catalina.core.StandardEngine/1.0";
private String mapperClass = "org.apache.catalina.core.StandardEngineMapper";
    
//当请求中没有指定服务器主机或未知主机时，要提供服务的默认主机名。
private String defaultHost = null;
//Engine属于的Service。一个Service只能包含一个Engine
private Service service = null;
//默认context的配置，在Host创建Context的时候进行配置
private DefaultContext defaultContext;
//Tomcat实例的JVM路由Id，唯一。在cluster中生成Session时，会用到
private String jvmRouteId;
```
###  StandardEngine的构造
```Java
public StandardEngine() {
        super();
        //将当前容器的标准实现阀加入到pipeline中
        pipeline.setBasic(new StandardEngineValve());
    }
```
### StandardEngine中的方法

![](https://upload-images.jianshu.io/upload_images/1916953-e197a2368753db3e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* void start()

直接使用继承ContainerBase中的start()方法
```java
public void start() throws LifecycleException {
        System.out.println(ServerInfo.getServerInfo());
        super.start();
    }
```

* addChild(Container child)

```java
public void addChild(Container child) {
        if (!(child instanceof Host)) {
            throw new IllegalArgumentException(sm.getString("standardEngine.notHost"));
        }
        super.addChild(child);
    }
```
* void addDefaultContext(DefaultContext defaultContext):设置Context创建时的配置
* importDefaultContext(Context context):使用DefaultContext配置Context

```java
public void addDefaultContext(DefaultContext defaultContext) {
        DefaultContext oldDefaultContext = this.defaultContext;
        this.defaultContext = defaultContext;
        support.firePropertyChange("defaultContext", oldDefaultContext, this.defaultContext);
    }
    
public void importDefaultContext(Context context) {
        if ( this.defaultContext != null ) {
            this.defaultContext.importDefaultContext(context);
        }
    }
```
StandardDefaultContext中的importDefaultContext(Context context) 方法：
```java
public void importDefaultContext(Context context) {

        if (context instanceof StandardContext) {
            ((StandardContext)context).setUseNaming(isUseNaming());
            ((StandardContext)context).setSwallowOutput(getSwallowOutput());
            if (!contexts.containsKey(context)) {
                ((StandardContext) context).addLifecycleListener(this);
            }
        }

        context.setCookies(getCookies());
        context.setCrossContext(getCrossContext());
        context.setReloadable(getReloadable());

        String [] listeners = findApplicationListeners();
        for( int i = 0; i < listeners.length; i++ ) {
            context.addApplicationListener(listeners[i]);
        }
        listeners = findInstanceListeners();
        for( int i = 0; i < listeners.length; i++ ) {
            context.addInstanceListener(listeners[i]);
        }
        String [] wrapper = findWrapperListeners();
        for( int i = 0; i < wrapper.length; i++ ) {
            context.addWrapperListener(wrapper[i]);
        }
        wrapper = findWrapperLifecycles();
        for( int i = 0; i < wrapper.length; i++ ) {
            context.addWrapperLifecycle(wrapper[i]);
        }
        String [] parameters = findParameters();
        for( int i = 0; i < parameters.length; i++ ) {
            context.addParameter(parameters[i],findParameter(parameters[i]));
        }
        ApplicationParameter [] appParam = findApplicationParameters();
        for( int i = 0; i < appParam.length; i++ ) {
            context.addApplicationParameter(appParam[i]);
        }

        if (!(context instanceof StandardContext)) {
            ContextEjb [] contextEjb = findEjbs();
            for( int i = 0; i < contextEjb.length; i++ ) {
                context.addEjb(contextEjb[i]);
            }
            ContextEnvironment [] contextEnv = findEnvironments();
            for( int i = 0; i < contextEnv.length; i++ ) {
                context.addEnvironment(contextEnv[i]);
            }
            /*
            if (context instanceof StandardContext) {
                ResourceParams [] resourceParams = findResourceParams();
                for( int i = 0; i < resourceParams.length; i++ ) {
                    ((StandardContext)context).addResourceParams
                        (resourceParams[i]);
                }
            }
            */
            ContextResource [] resources = findResources();
            for( int i = 0; i < resources.length; i++ ) {
                context.addResource(resources[i]);
            }
            String [] envRefs = findResourceEnvRefs();
            for( int i = 0; i < envRefs.length; i++ ) {
                context.addResourceEnvRef
                    (envRefs[i],findResourceEnvRef(envRefs[i]));
            }
        }

    }
```

## StandardContextValve
```java
final class StandardEngineValve extends ValveBase {
    // ----------------------------------------------------- Instance Variables
    private static final String info = "org.apache.catalina.core.StandardEngineValve/1.0";
    private static final StringManager sm =
        StringManager.getManager(Constants.Package);
    // ------------------------------------------------------------- Properties
    public String getInfo() {
        return (info);
    }
    // --------------------------------------------------------- Public Methods
    /**
     * 基于Request的Server name选择合适的Host子容器来处理Request。
     * 如果没有找到匹配的Host，返回Error
     */
    public void invoke(Request request, Response response,
                       ValveContext valveContext)
        throws IOException, ServletException {
        //检查request与response的类型
        if (!(request.getRequest() instanceof HttpServletRequest) ||
            !(response.getResponse() instanceof HttpServletResponse)) {
            return;     
        }

        HttpServletRequest hrequest = (HttpServletRequest) request;
        //Request的protocol为 HTTP/1.1,且ServerName为null，返回400
        if ("HTTP/1.1".equals(hrequest.getProtocol()) &&
            (hrequest.getServerName() == null)) {
            ((HttpServletResponse) response.getResponse()).sendError
                (HttpServletResponse.SC_BAD_REQUEST,
                 sm.getString("standardEngine.noHostHeader",
                              request.getRequest().getServerName()));
            return;
        }
        // Select the Host to be used for this Request
        StandardEngine engine = (StandardEngine) getContainer();
        //调用Engine的map方法选择Host
        Host host = (Host) engine.map(request, true);
        if (host == null) {
            ((HttpServletResponse) response.getResponse()).sendError
                (HttpServletResponse.SC_BAD_REQUEST,
                 sm.getString("standardEngine.noHost",
                              request.getRequest().getServerName()));
            return;
        }
        //Host继续处理Request
        host.invoke(request, response);
    }

}

```