[TOC]


Context的父容器通常是Host容器，也有可能可能是其他实现，或者如果不必要，就可以不使用父容器。

## 1.  Host

* Host虚拟主机的作用，是运行多个Web应用（一个Context代表一个Web应用），并负责安装、展开、启动和结束每个Web应用。
* Host组件代表的虚拟主机，对应了服务器中一个网络名实体(如”www.test.com”，或IP地址”116.25.25.25”)；为了使用户可以通过网络名连接Tomcat服务器，这个名字应该在DNS服务器上注册。

下面是Host提供的方法：
```java
public interface Host extends Container {
    // ----------------------------------------------------- Manifest Constants
    //Alias Evnet Type
    public static final String ADD_ALIAS_EVENT = "addAlias";
    public static final String REMOVE_ALIAS_EVENT = "removeAlias";


    // ------------------------------------------------------------- Properties
    //返回此主机的应用程序根目录。这可以是绝对路径名、相对路径名或URL。
    public String getAppBase();
    public void setAppBase(String appBase);
    //返回AutoDebug部署标志的值。如果为真，则表明该主机的子WebApp应该被发现并自动部署。
    public boolean getAutoDeploy();
    public void setAutoDeploy(boolean autoDeploy);
    //添加默认的Context
    public void addDefaultContext(DefaultContext defaultContext);
    public DefaultContext getDefaultContext();
    //返回此容器表示的虚拟主机的规范、完全限定名。用于展示
    public String getName();
    public void setName(String name);

    // --------------------------------------------------------- Public Methods
    public void importDefaultContext(Context context);
    //Host的alias name,匹配多个Request路径到同一个Host
    public void addAlias(String alias);
    public String[] findAliases();
    public void removeAlias(String alias);
    //返回用于处理指定Host请求URI的Context
    public Context map(String uri);
}
```

## 2. Host的标准实现StandardHost

### 2.1 StandardHost的类关系图

![image.png](https://upload-images.jianshu.io/upload_images/1916953-bc30f966f21fd115.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 2.2 StandardHost的参数

![image.png](https://upload-images.jianshu.io/upload_images/1916953-f77e83043f5d68cb.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 2.3 StandardHost的构造体

在构造体中添加StandardHostValve作为基础阀
```java
public StandardHost() {
        super();
        pipeline.setBasic(new StandardHostValve());
    }
```

### 2.4 StandardHost中的方法

#### ContainerBase相关
* void start()：StandardHost重写了ContainerBase的start方法
* void addChild(Container child)
```java
public synchronized void start() throws LifecycleException {
        //为Host的Pipeline添加两个Valve，分别为：errorReportValve,ErrorDispatcherValve
        if ((errorReportValveClass != null) && (!errorReportValveClass.equals(""))) {
            try {
                Valve valve = (Valve) Class.forName(errorReportValveClass).newInstance();
                addValve(valve);
            } catch (Throwable t) {
                log(sm.getString("standardHost.invalidErrorReportValveClass", errorReportValveClass));
            }
        }
        addValve(new ErrorDispatcherValve());
        super.start();
    }

public void addChild(Container child) {
        //Host在添加子容器时的检查
        if (!(child instanceof Context)) {
            throw new IllegalArgumentException (sm.getString("standardHost.notContext"));
        }
        super.addChild(child);
    }
```

#### Host接口相关：

根据Uri查找匹配的Context：
*  Context map(String uri) 
```java
public Context map(String uri) {
        if (debug > 0) {
            log("Mapping request URI '" + uri + "'");
        }
        if (uri == null) {
            return (null);
        }
        // Match on the longest possible context path prefix
        if (debug > 1)
            log("  Trying the longest context path prefix");
        Context context = null;
        String mapuri = uri;
        //  /app1/a/b/c--> /app1/a/b --> /app1/a
        while (true) {
            context = (Context) findChild(mapuri);
            if (context != null)
                break;
            int slash = mapuri.lastIndexOf('/');
            if (slash < 0)
                break;
            mapuri = mapuri.substring(0, slash);
        }
        // If no Context matches, select the default Context
        if (context == null) {
            if (debug > 1)
                log("  Trying the default context");
            //上面匹配不到Context，这里尝试匹配默认的Context
            context = (Context) findChild("");
        }
        // 都匹配不到，返回 null
        if (context == null) {
            log(sm.getString("standardHost.mappingError", uri));
            return (null);
        }
        // 返回匹配到的Context
        if (debug > 0)
            log(" Mapped to context '" + context.getPath() + "'");
        return (context);
    }
```

DefaultContext元素表示Context的配置的子集，并且可以嵌套在Engine与Host元素中。用来配置创建的Context。

* void addDefaultContext(DefaultContext defaultContext)：添加一个默认的context的config
* void importDefaultContext(Context context)：导入默认的context的config到一个应用中的context

```java
public void addDefaultContext(DefaultContext defaultContext) {
        DefaultContext oldDefaultContext = this.defaultContext;
        this.defaultContext = defaultContext;
        support.firePropertyChange("defaultContext", oldDefaultContext, this.defaultContext);
    }

public void importDefaultContext(Context context) {
        if( this.defaultContext != null ) {
            this.defaultContext.importDefaultContext(context);
        }
    }
```

设置Host的别名
* addAlias(String alias)
* void removeAlias(String alias)
* String[] findAliases()
```java
public void addAlias(String alias) {
        //转换为小写
        alias = alias.toLowerCase();

        for (int i = 0; i < aliases.length; i++) {
            if (aliases[i].equals(alias))
                return;
        }
        //存储
        String newAliases[] = new String[aliases.length + 1];
        for (int i = 0; i < aliases.length; i++)
            newAliases[i] = aliases[i];
        newAliases[aliases.length] = alias;
        aliases = newAliases;
        //触发ADD_ALIAS_EVENT事件
        fireContainerEvent(ADD_ALIAS_EVENT, alias);
    }

public void removeAlias(String alias) {
        //转换为小写
        alias = alias.toLowerCase();
        synchronized (aliases) {
            // 确保别名是存在的
            int n = -1;
            for (int i = 0; i < aliases.length; i++) {
                if (aliases[i].equals(alias)) {
                    n = i;
                    break;
                }
            }
            if (n < 0)
                return;
            // 删除
            int j = 0;
            String results[] = new String[aliases.length - 1];
            for (int i = 0; i < aliases.length; i++) {
                if (i != n)
                    results[j++] = aliases[i];
            }
            aliases = results;
        }
        // 触发REMOVE_ALIAS_EVENT
        fireContainerEvent(REMOVE_ALIAS_EVENT, alias);

    }

public String[] findAliases() {
        return (this.aliases);
    }
```

#### Deployer相关的接口

Deployer相关的接口都是通过StandardHostDeployer来实现的。Deployer是Tomcat的部署的内嵌容器，具有一下特点：

1. Deployer是一个特殊的容器，用来部署和取消部署web程序
2. 对于每一个需要部署的容器，Deployer都会创建或者安装一个Context实例(每个Context代表我们一个web应用程序)
3. 每个应用程序的唯一的关键，是它的Context的路径

#详见[]------//todo

* void install(String contextPath, URL war)
* void install(URL config, URL war)
* Context findDeployedApp(String contextPath)
* String[] findDeployedApps()
* void remove(String contextPath)

```java
public void install(String contextPath, URL war) throws IOException {
        deployer.install(contextPath, war);
    }

public synchronized void install(URL config, URL war) throws IOException {
        deployer.install(config, war);
    }

public Context findDeployedApp(String contextPath) {
        return (deployer.findDeployedApp(contextPath));
    }

public String[] findDeployedApps() {
        return (deployer.findDeployedApps());
}

public void remove(String contextPath) throws IOException {
        deployer.remove(contextPath);
    }
```

* void start(String contextPath)
* stop(String contextPath)

```java
public void start(String contextPath) throws IOException {
        deployer.start(contextPath);
    }

public void stop(String contextPath) throws IOException {
        deployer.stop(contextPath);
    }
```



参考：
1. 《深入剖析Tomcat》
2. [详解Tomcat 配置文件server.xml](https://www.cnblogs.com/kismetv/p/7228274.html)
