

[TOC]

## ContainerBase
### 1. ContainerBase的类关系图

ContainerBase是容器接口的抽象实现，提供几乎所有实现所需的公共功能。

ContainerBase实现了下面的三个类中的方法，来完善自己的功能。

Container：提供对于组成容器基本构件的管理，如：loader、logger、cluster、realm、resources等
LifeCycle：提供对于容器的生命周期管理方法
Pipeline：提供对于Request处理流水线

![ContainerBase类关系图.png](https://upload-images.jianshu.io/upload_images/1916953-5e9d0b1f6d158755.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 2. ContainerBase的相关参数
```java
protected HashMap children = new HashMap();	//存储子容器的Map
protected int debug = 0;					//日志等级，数值越高，日志越详细
protected LifecycleSupport lifecycle = new LifecycleSupport(this);	//声明周期管理类
protected ArrayList listeners = new ArrayList();//当前容器的事件监听器
protected Loader loader = null;				//与该容器绑定的Java类加载器
protected Logger logger = null;				//与该容器绑定的Logger日志记录器
protected Manager manager = null;			//与该容器绑定的Session管理器
protected Cluster cluster = null;			//与该容器绑定的CLuster
protected Mapper mapper = null;				//与该容器绑定的Mapper资源映射器。如果该值不为null，是不会由下面的mappers中取mapper的
protected HashMap mappers = new HashMap();	//与此容器相关联的映射器，key为protocol。
protected String mapperClass = null;		//默认Mapper的Java Class Name
protected String name = null;				//自定义的该容器的名字，不影响代码逻辑
protected Container parent = null;			//当前容器的父容器
protected ClassLoader parentClassLoader = null;	//在安装加载器时要配置的父类加载器。
protected Pipeline pipeline = new StandardPipeline(this);	//当前容器的pipeline
protected Realm realm = null;				//
protected DirContext resources = null;		//设置Context的访问路径，包括hostName，contextName等
protected static StringManager sm = StringManager.getManager(Constants.Package);//日志管理类
protected boolean started = false;			//当前容器是否已经启动
protected PropertyChangeSupport support = new PropertyChangeSupport(this);	//当前容器属性更改支持
```

### 3. ContainerBase提供的基本方法功能：

![图片.png](https://upload-images.jianshu.io/upload_images/1916953-9e2a83679e9cfe0c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 3.1  实现的LifeCycle相关方法

LIfeCycle相关方法的实现，都是通过 LifecycleSupport 类来完成的。LifeCycleSupport是观察者模式的实现：

通过数组的形式管理每个Listener对象，提供添加与删除一个LifeListener对象的方法；同时提供获取所有Listener的方法；提供了通过Event触发所有Listener的方法(处不处理看内部实现)。

ContianerBase实现了Lifecycle类中的接口，借助于 LifecycleSupport 实现了其中的方法。

先看下containerBase中的 start与stop方法的实现：
* void start()
```java
public synchronized void start() throws LifecycleException {
        // 重复start抛出异常
        if (started)
            throw new LifecycleException (sm.getString("containerBase.alreadyStarted", logName()));
        // 触发BEFORE_START_EVENT事件
        lifecycle.fireLifecycleEvent(BEFORE_START_EVENT, null);
        //添加默认的mapper
        addDefaultMapper(this.mapperClass);
        started = true;
        // start任何的从属组件
        if ((loader != null) && (loader instanceof Lifecycle))
            ((Lifecycle) loader).start();
        if ((logger != null) && (logger instanceof Lifecycle))
            ((Lifecycle) logger).start();
        if ((manager != null) && (manager instanceof Lifecycle))
            ((Lifecycle) manager).start();
        if ((cluster != null) && (cluster instanceof Lifecycle))
            ((Lifecycle) cluster).start();
        if ((realm != null) && (realm instanceof Lifecycle))
            ((Lifecycle) realm).start();
        if ((resources != null) && (resources instanceof Lifecycle))
            ((Lifecycle) resources).start();
        //启动所有Wrapper
        Mapper mappers[] = findMappers();
        for (int i = 0; i < mappers.length; i++) {
            if (mappers[i] instanceof Lifecycle)
                ((Lifecycle) mappers[i]).start();
        }
        // 启动所有的子容器
        Container children[] = findChildren();
        for (int i = 0; i < children.length; i++) {
            if (children[i] instanceof Lifecycle)
                ((Lifecycle) children[i]).start();
        }
        // 启动pipeline中的Valve
        if (pipeline instanceof Lifecycle)
            ((Lifecycle) pipeline).start();
        // 触发START_EVENT事件
        lifecycle.fireLifecycleEvent(START_EVENT, null);
        // 触发AFTER_START_EVENT
        lifecycle.fireLifecycleEvent(AFTER_START_EVENT, null);
    }
```
* void stop()
```java
public synchronized void stop() throws LifecycleException {
        //重复stop抛出
        if (!started)
            throw new LifecycleException
                (sm.getString("containerBase.notStarted", logName()));
        // 触发BEFORE_STOP_EVENT事件
        lifecycle.fireLifecycleEvent(BEFORE_STOP_EVENT, null);
        // 触发STOP_EVENT事件
        lifecycle.fireLifecycleEvent(STOP_EVENT, null);
        started = false;
        // 停止所有的pipeline
        if (pipeline instanceof Lifecycle) {
            ((Lifecycle) pipeline).stop();
        }
        // stop子容器
        Container children[] = findChildren();
        for (int i = 0; i < children.length; i++) {
            if (children[i] instanceof Lifecycle)
                ((Lifecycle) children[i]).stop();
        }
        // stop mappers
        Mapper mappers[] = findMappers();
        for (int i = 0; i < mappers.length; i++) {
            if (mappers[(mappers.length-1)-i] instanceof Lifecycle)
                ((Lifecycle) mappers[(mappers.length-1)-i]).stop();
        }
        // stop从属组件
        if ((resources != null) && (resources instanceof Lifecycle)) {
            ((Lifecycle) resources).stop();
        }
        if ((realm != null) && (realm instanceof Lifecycle)) {
            ((Lifecycle) realm).stop();
        }
        if ((cluster != null) && (cluster instanceof Lifecycle)) {
            ((Lifecycle) cluster).stop();
        }
        if ((manager != null) && (manager instanceof Lifecycle)) {
            ((Lifecycle) manager).stop();
        }
        if ((logger != null) && (logger instanceof Lifecycle)) {
            ((Lifecycle) logger).stop();
        }
        if ((loader != null) && (loader instanceof Lifecycle)) {
            ((Lifecycle) loader).stop();
        }
        // 触发AFTER_STOP_EVENT事件
        lifecycle.fireLifecycleEvent(AFTER_STOP_EVENT, null);

    }
```

下面简单看下LifecycleSupport的Add与fire方法的实现，具体的内容看下面的Lifecycle：
* addLifecycleListener(LifecycleListener listener): 添加LifecycleListener监听器

```java
public void addLifecycleListener(LifecycleListener listener) {

      synchronized (listeners) {
          LifecycleListener results[] =
            new LifecycleListener[listeners.length + 1];
          for (int i = 0; i < listeners.length; i++)
              results[i] = listeners[i];
          results[listeners.length] = listener;
          listeners = results;
      }

    }
```
* findLifecycleListeners():获取Lifecycle所有的Listener
* removeLifecycleListener(LifecycleListener listener):移除一个LifecycleListener
* fireLifecycleEvent(String type, Object data)：触发相关的监听器

```java
public void fireLifecycleEvent(String type, Object data) {

        LifecycleEvent event = new LifecycleEvent(lifecycle, type, data);
        LifecycleListener interested[] = null;
        synchronized (listeners) {
            interested = (LifecycleListener[]) listeners.clone();
        }
        for (int i = 0; i < interested.length; i++)
            interested[i].lifecycleEvent(event);

    }
```

#### 3.2 实现的Pipeline 的相关方法

pipeline代表的是当前容器对于请求处理的流水线过程。

pipeline中包含许多的Valve，当容器invoke方法被调用时，就会通过Pipeline执行里面的各个Valve。pipeline必须有一个Value，因为需要处理request以及完成响应的response。

一般各个组件都会存在一个StandardValve类，来作为pipeline的basic value。

在ContainerBase中，借助于Pipeline的StanderPipeline，实现了Pipeline的接口，实际操作都是在StandardPipeline中。

* void addValve(Valve valve)

```java
public synchronized void addValve(Valve valve) {
        pipeline.addValve(valve);
        fireContainerEvent(ADD_VALVE_EVENT, valve);
    }
```
* void removeValve(Valve valve)

```java
public synchronized void removeValve(Valve valve) {
        pipeline.removeValve(valve);
        fireContainerEvent(REMOVE_VALVE_EVENT, valve);
    }
```
Pipeline的具体说明在下面。


#### 3.3 实现Container提供的相关方法

相关方法的实现，比较简单又大同小异，这里只给出一些的实现，剩下的大家都能够猜的出来：

* 子容器相关
	* addChild(Container child)
	* removeChild(Container child)
	* findChild(String name)
	* findChildren()

```java
public void addChild(Container child) {
        //是否设置了安全管理器
        if (System.getSecurityManager() != null) {
            PrivilegedAction dp =
                new PrivilegedAddChild(child);
            AccessController.doPrivileged(dp);
        } else {
            addChildInternal(child);
        }
    }

    private void addChildInternal(Container child) {

        synchronized(children) {
            if (children.get(child.getName()) != null)
                throw new IllegalArgumentException("addChild:  Child name '" + child.getName() + "' is not unique");
            //子容器设置父容器
            child.setParent((Container) this);  // May throw IAE
            if (started && (child instanceof Lifecycle)) {
                try {
                    //调用start方法
                    ((Lifecycle) child).start();
                } catch (LifecycleException e) {
                    log("ContainerBase.addChild: start: ", e);
                    throw new IllegalStateException ("ContainerBase.addChild: start: " + e);
                }
            }
            //子容器一般可以设置多个，根据名字记录
            children.put(child.getName(), child);
            //触发ADD_CHILD_EVENT事件
            fireContainerEvent(ADD_CHILD_EVENT, child);
        }

    }
```
* 当前容器的事件处理
	* addContainerListener(ContainerListener listener)
	* removeContainerListener(ContainerListener listener)
	* findContainerListeners()
* Mapper相关：
	* addMapper(Mapper mapper)
	* removeMapper(Mapper mapper)
	* findMapper(String protocol)
	* findMappers()
	* map(Request request, boolean update)

```java
public void addMapper(Mapper mapper) {
        synchronized(mappers) {
            //如果该Mapper对应Protocol，已经存在另一个Mapper也是该Protocol，抛出异常
            if (mappers.get(mapper.getProtocol()) != null)
                throw new IllegalArgumentException("addMapper:  Protocol '" +
                                                   mapper.getProtocol() +
                                                   "' is not unique");
            //为该mapper关联Container
            mapper.setContainer((Container) this);      // May throw IAE
            if (started && (mapper instanceof Lifecycle)) {
                try {
                    //如果容器已经启动，在加入mapper之前，调用该mapper方法
                    ((Lifecycle) mapper).start();
                } catch (LifecycleException e) {
                    log("ContainerBase.addMapper: start: ", e);
                    throw new IllegalStateException ("ContainerBase.addMapper: start: " + e);
                }
            }
            //procotcol时请求协议
            mappers.put(mapper.getProtocol(), mapper);
            //this.mapper代表默认添加的mapper，如果mappers中存在一个mapper，它就是默认的，如果添加多个，默认的mapper置为null
            if (mappers.size() == 1)
                this.mapper = mapper;
            else
                this.mapper = null;
            //触发ADD_MAPPER_EVENT事件
            fireContainerEvent(ADD_MAPPER_EVENT, mapper);
        }
    }
    
    
    public Container map(Request request, boolean update) {

        // 根据Protocol选择合适的资源映射器
        Mapper mapper = findMapper(request.getRequest().getProtocol());
        if (mapper == null)
            return (null);

        // 通过该资源映射器选择合适的容器处理请求
        return (mapper.map(request, update));
    }
```
* 组件属性更改事件处理
	 组件属性的更改是指当前容器的某些基本属性被更改时，通知各个监听器。
	 当前有debug, loader, logger, manager, cluster, name, parent, parentClassLoader, realm, resources：	 
	* addPropertyChangeListener(PropertyChangeListener listener)
	* removePropertyChangeListener(PropertyChangeListener listener)

## StandardPipleline

处理管道的标准实现，该管道将调用一系列已配置为按顺序调用的阀门。此实现可用于任何类型的容器。
### 1. Standard的类关系图
![图片.png](https://upload-images.jianshu.io/upload_images/1916953-606417562564bc7c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### 2. StandardPipeline的参数
```java
protected Valve basic = null;           //basic valve
protected Container container = null;   //相关的容器
protected LifecycleSupport lifecycle = new LifecycleSupport(this);  //生命周期管理
protected boolean started = false;      //是否启动
protected Valve valves[] = new Valve[0];// valve数组    
```
### 3. StandardPipeline提供的方法功能。

![图片.png](https://upload-images.jianshu.io/upload_images/1916953-f5784f57ae8db4ec.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 3.1 Lifecycle相关方法

* start方法，Pipeline的启动实现
```java
public synchronized void start() throws LifecycleException {
        //之前已经启动，再次启动会抛出异常
        if (started)
            throw new LifecycleException
                (sm.getString("standardPipeline.alreadyStarted"));
        // 触发BEFORE_START_EVENT时间
        lifecycle.fireLifecycleEvent(BEFORE_START_EVENT, null);
        //重置标志位
        started = true;
        // 启动pipeline中的每一个阀(Valve)
        for (int i = 0; i < valves.length; i++) {
            if (valves[i] instanceof Lifecycle)
                ((Lifecycle) valves[i]).start();
        }
        if ((basic != null) && (basic instanceof Lifecycle))
            ((Lifecycle) basic).start();
        // 触发START_EVENT
        lifecycle.fireLifecycleEvent(START_EVENT, null);
        // 触发AFTER_START_EVENT
        lifecycle.fireLifecycleEvent(AFTER_START_EVENT, null);
    }
```
* stop方法，Pipeline的终止实现
```java
public synchronized void stop() throws LifecycleException {
        //如果之前已经关闭，再次关闭会抛出异常
        if (!started)
            throw new LifecycleException(sm.getString("standardPipeline.notStarted"));
        // 触发BEFORE_STOP_EVENT事件
        lifecycle.fireLifecycleEvent(BEFORE_STOP_EVENT, null);
        // 触发STOP_EVENT
        lifecycle.fireLifecycleEvent(STOP_EVENT, null);
        // 重置标志位
        started = false;
        // 停止所有的Valve
        if ((basic != null) && (basic instanceof Lifecycle))
            ((Lifecycle) basic).stop();
        for (int i = 0; i < valves.length; i++) {
            if (valves[i] instanceof Lifecycle)
                ((Lifecycle) valves[i]).stop();
        }
        //触发AFTER_STOP_EVENT方法
        lifecycle.fireLifecycleEvent(AFTER_STOP_EVENT, null);
    }
```
* 监听器Listener相关的操作，这个也是借助于LifecycleSupport来实现的
``` java
    //添加一个监听器Listener
    public void addLifecycleListener(LifecycleListener listener) {
        lifecycle.addLifecycleListener(listener);
    }
    //获取所有Listener
    public LifecycleListener[] findLifecycleListeners() {
        return lifecycle.findLifecycleListeners();
    }
    //删除所有的Listener
    public void removeLifecycleListener(LifecycleListener listener) {
        lifecycle.removeLifecycleListener(listener);
    }
```
#### 3.2 Pipeline相关的接口

* invoke(Request request, Response response)：由与此管道关联的阀门Valve处理请求和响应，直到其中一个阀门创建和返回响应。实现必须确保多个同时请求（在不同线程上）可以通过同一管道进行处理，而不会干扰彼此的控制流。
```java
public void invoke(Request request, Response response)
        throws IOException, ServletException {
        // Invoke the first Valve in this pipeline for this request
        (new StandardPipelineValveContext()).invokeNext(request, response);
    }
protected class StandardPipelineValveContext
        implements ValveContext {
        //经过的第几个Valve
        protected int stage = 0;
        public String getInfo() {
            return info;
        }
        public void invokeNext(Request request, Response response)throws IOException, ServletException {
            int subscript = stage;
            stage = stage + 1;
            //如果当前经过的Valve个数小于总个数，继续调用下一个Valve的invoke方法
            if (subscript < valves.length) {
                valves[subscript].invoke(request, response, this);
            //当所有的Valve都调用完成。调用最后的basic Valve
            } else if ((subscript == valves.length) && (basic != null)) {
                basic.invoke(request, response, this);
            } else {
                throw new ServletException
                    (sm.getString("standardPipeline.noValve"));
            }
        }
    }
```

* void addValve(Valve valve)：为Pipeline添加一个阀Valve
```java
public void addValve(Valve valve) {
        if (valve instanceof Contained)
            ((Contained) valve).setContainer(this.container);
        // 如果Pipeline已经启动，那么在添加Valve之前启动它
        if (started && (valve instanceof Lifecycle)) {
            try {
                ((Lifecycle) valve).start();
            } catch (LifecycleException e) {
                log("StandardPipeline.addValve: start: ", e);
            }
        }
        // 采用copy为新数组的方法，进行添加
        synchronized (valves) {
            Valve results[] = new Valve[valves.length +1];
            System.arraycopy(valves, 0, results, 0, valves.length);
            results[valves.length] = valve;
            valves = results;
        }
    }
```
* removeValve(Valve valve)：为Pipeline移除一个阀Valve

```java
public void removeValve(Valve valve) {
        synchronized (valves) {
            // 定位要删除的Valve在数组中的位置 j
            int j = -1;
            for (int i = 0; i < valves.length; i++) {
                if (valve == valves[i]) {
                    j = i;
                    break;
                }
            }
            if (j < 0)
                return;
            
            // 将其他的Valve放入到新数组中
            Valve results[] = new Valve[valves.length - 1];
            int n = 0;
            for (int i = 0; i < valves.length; i++) {
                if (i == j)
                    continue;
                results[n++] = valves[i];
            }
            valves = results;
            try {
                if (valve instanceof Contained)
                    ((Contained) valve).setContainer(null);
            } catch (Throwable t) {
            }
        }
        // 停止这个Valve
        if (started && (valve instanceof Lifecycle)) {
            try {
                ((Lifecycle) valve).stop();
            } catch (LifecycleException e) {
                log("StandardPipeline.removeValve: stop: ", e);
            }
        }
    }
```

## Lifecycle

Lifecycle提供了生命周期的接口，但是具体的实现需要通过LifecycleSupport的支持完成的
```java
public interface Lifecycle {
    public static final String START_EVENT = "start";
    public static final String BEFORE_START_EVENT = "before_start";
    public static final String AFTER_START_EVENT = "after_start";
    public static final String STOP_EVENT = "stop";
    public static final String BEFORE_STOP_EVENT = "before_stop";
    public static final String AFTER_STOP_EVENT = "after_stop";

    public void addLifecycleListener(LifecycleListener listener);
    public LifecycleListener[] findLifecycleListeners();
    public void removeLifecycleListener(LifecycleListener listener);

    public void start() throws LifecycleException;
    public void stop() throws LifecycleException;
}
```

#### LifecycleSupport

LIfecycleSupport实现比较简单，一共就四个方法，简单看下

* addLifecycleListener(LifecycleListener listener)
```java
public void addLifecycleListener(LifecycleListener listener) {
      synchronized (listeners) {
          LifecycleListener results[] =
            new LifecycleListener[listeners.length + 1];
          for (int i = 0; i < listeners.length; i++)
              results[i] = listeners[i];
          results[listeners.length] = listener;
          listeners = results;
      }

    }
```
* removeLifecycleListener(LifecycleListener listener)
 ```java
public void removeLifecycleListener(LifecycleListener listener) {
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
            LifecycleListener results[] =
              new LifecycleListener[listeners.length - 1];
            int j = 0;
            for (int i = 0; i < listeners.length; i++) {
                if (i != n)
                    results[j++] = listeners[i];
            }
            listeners = results;
        }
    }
 ```

* findLifecycleListeners()


```java
public LifecycleListener[] findLifecycleListeners() {
        return listeners;
    }
```
* fireLifecycleEvent(String type, Object data)

```java
public void fireLifecycleEvent(String type, Object data) {
        LifecycleEvent event = new LifecycleEvent(lifecycle, type, data);
        LifecycleListener interested[] = null;
        synchronized (listeners) {
            interested = (LifecycleListener[]) listeners.clone();
        }
        for (int i = 0; i < interested.length; i++)
            interested[i].lifecycleEvent(event);
    }
```