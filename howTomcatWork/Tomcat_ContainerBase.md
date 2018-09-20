

###ContainerBase的类关系图

Container：提供对于组成容器各个
LifeCycle：
Pipeline：

![ContainerBase类关系图.png](https://upload-images.jianshu.io/upload_images/1916953-5e9d0b1f6d158755.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### ContainerBase的相关参数
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