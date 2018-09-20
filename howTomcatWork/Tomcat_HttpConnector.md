> 前言：
>
> 1. 本文章是在读完《深入剖析Tomcat》一书，结合源码做的总结，这里强烈推荐一下这本书，真的非常好！
> 2. 文章有很多是自己的理解，我是感觉是这样的,欢迎指正。
> 3. 本文章用到的Tomcat源码是Tomcat 4的代码
> 4. 文章用到的源码可以通过 [org/apache](https://github.com/Oaks907/bookspace/tree/master/howTomcatWork/src/main/java)获取,org/apache目录下就是

[TOC]


![tomcat 核心架构](https://camo.githubusercontent.com/ee261fff724bf473ff69c80a946617b0b9ef46d4/687474703a2f2f646c322e69746579652e636f6d2f75706c6f61642f6174746163686d656e742f303038362f323232322f32366564336561392d633663302d333330302d383433642d6661646330353232613538382e706e67)

* HttpConnector是Tomcat的连接器，内部通过Socket来监听一个地址端口；
* 它的内部维持了一个HttpProcessor集合，每当一个请求过来的时候，HttpConnector就会将该Socket连接分配一个Processor去处理这个Request请求；
* Processor内部会通过调用绑定的HttpConnector获取HttpRequest与HttpResponse对象，通过解析Socket已经里面的InputStream，outputStream，来填充HttpRequest与HttpResponse中的各种字段，包括解析请求行与请求头；
* 完成解析工作之后会获取上层容器继续处理Request与Response，即如上面的架构图一样，继续传递
* 上层容器层层处理，最后将结果Response通过Socket的OutputStream返回给client


### HttpConnector的作用

1. 通过Socket监听请求，并将监听的request请求传递给下一个选手HttpProcessor处理
2. 创建HttpRequest与HttpResponse对象，HttpRequest用于request请求的传递，HttpResponse返回请求的数据

**Tomcat4.0 HttpConnector的参数**

```java
private Service service = null;         //Service:connector的集合，可以通过分享统一个Container来处理请求
private int acceptCount = 10;           //该Connector的最大连接数
private String address = null;          //绑定的IP地址
private int bufferSize = 2048;          //
protected Container container = null;   //父容器
private Vector created = new Vector();  //已创建Processor的集合
private int curProcessors = 0;          //已创建Processor的数量
private int debug = 0;                  //日志级别
private boolean enableLookups = false;  //是否允许DNS查找
private ServerSocketFactory factory = null; //SocketFacotry
private static final String info = "org.apache.catalina.connector.http.HttpConnector/1.0";  
protected LifecycleSupport lifecycle = new LifecycleSupport(this);      //生命周期管理
protected int minProcessors = 5;        //初始化时Processor的最小启动数量
private int maxProcessors = 20;         //Processor的最大启动数量
private int connectionTimeout = Constants.DEFAULT_CONNECTION_TIMEOUT;
private int port = 8081;                //HttpConnector的监听端口号
private Stack processors = new Stack(); //已创建但是还没有被使用的Processor集合
private String proxyName = null;        //分配给该Connector的request的来源服务器名称。当在代理服务器之后操作Tomcat的时候，这个字段是十分有用的，以便重定向得到正确的构建.如果没有指定，这个名字默认取自Header的Host字段.在设置了ProxyName与ProxyPort后(Tomcat中配置了代理)，在Web应用中的servlet任务，所有的代理请求都是指向 proxyPort端口的proxyName
private int proxyPort = 0;              //request的来源端口，如果没有指定，默认取值port
private int redirectPort = 443;             //non-SSL --> SSL的重定向端口
private String scheme = "http";             //通过该Connector接收到的request的默认请求协议
private boolean secure = false;             //
private ServerSocket serverSocket = null;   //监听端口使用的ServerSocket
private StringManager sm = StringManager.getManager(Constants.Package); //日志Log管理
private boolean initialized = false;        //是否initialized标志位
private boolean started = false;            //是否started标志位
private boolean stopped = false;            //是否stoped标志位
private Thread thread = null;               //Daemon守护线程
private String threadName = null;           //Daemon守护线程设置的Name
private Object threadSync = new Object();   //线程同步Object，没有其他作用
private boolean allowChunking = true;       //Is chunking allowed ?(不知道干嘛的？)
private boolean tcpNoDelay = true;          //设置启用Nagle算法，减少网络中发送小数据包的数量
```

### HttpConnector启动过程：

#### HttpConnector的初始化

HttpConnector的初始化的操作还是蛮简单的。就是简单置了一下initialized状态位，同时调用open()方法通过ServerSocketFactory创建一个ServerSocket。

```java
	public void initialize() throws LifecycleException {
    if (initialized)
      throw new LifecycleException(
        sm.getString("httpConnector.alreadyInitialized"));

    this.initialized = true;
    Exception eRethrow = null;

    // Establish a server socket on the specified port
    try {
      serverSocket = open();
    } catch (IOException ioe) {
      log("httpConnector, io problem: ", ioe);
      eRethrow = ioe;
    } catch (KeyStoreException kse) {
      log("httpConnector, keystore problem: ", kse);
      eRethrow = kse;
    } catch (NoSuchAlgorithmException nsae) {
      log("httpConnector, keystore algorithm problem: ", nsae);
      eRethrow = nsae;
    } catch (CertificateException ce) {
      log("httpConnector, certificate problem: ", ce);
      eRethrow = ce;
    } catch (UnrecoverableKeyException uke) {
      log("httpConnector, unrecoverable key: ", uke);
      eRethrow = uke;
    } catch (KeyManagementException kme) {
      log("httpConnector, key management problem: ", kme);
      eRethrow = kme;
    }

    if (eRethrow != null)
      throw new LifecycleException(threadName + ".open", eRethrow);

  }
```

创建ServerSocket的open方法

```java
private ServerSocket open() throws IOException, KeyStoreException, NoSuchAlgorithmException,
    CertificateException, UnrecoverableKeyException, KeyManagementException {

    // Acquire the server socket factory for this Connector
    ServerSocketFactory factory = getFactory();

    // If no address is specified, open a connection on all addresses
    if (address == null) {
      log(sm.getString("httpConnector.allAddresses"));
      try {
        return (factory.createSocket(port, acceptCount));
      } catch (BindException be) {
        throw new BindException(be.getMessage() + ":" + port);
      }
    }

    // Open a server socket on the specified address
    try {
      //在指定的IP地址上，创建Server Socket,如果address为null时，初始化得到的socket的地址为0.0.0.0，一台主机还没有被分配一个IP地址的时候，用于表示主机本身
      InetAddress is = InetAddress.getByName(address);
      log(sm.getString("httpConnector.anAddress", address));
      try {
        return (factory.createSocket(port, acceptCount, is));
      } catch (BindException be) {
        throw new BindException(be.getMessage() + ":" + address +
          ":" + port);
      }
    } catch (Exception e) {
      log(sm.getString("httpConnector.noAddress", address));
      try {
        return (factory.createSocket(port, acceptCount));
      } catch (BindException be) {
        throw new BindException(be.getMessage() + ":" + port);
      }
    }
  }
```

#### HttpConnector的启动

HttpConnector的启动与终止都是通过Lifecycle生命周期管理接口来实现的，通过实现里面的start() stop()接口。

start()方法
  1. 判断HttpConnector是否启动，重置started状态位，并调用Lifecycle发布START_EVENT事件
  2. 调用threadStart方法，来设置Daemon守护线程，并且启动当前线程进行Socket监听
  3. 创建设置的最小的HttpProcessor数量，同时调用recycle方法，来回收创建的processor(置入processors，表示已创建但未使用) 
```java
public void start() throws LifecycleException {

        // Validate and update our current state
        if (started)
            throw new LifecycleException
                (sm.getString("httpConnector.alreadyStarted"));
        threadName = "HttpConnector[" + port + "]";
        lifecycle.fireLifecycleEvent(START_EVENT, null);
        started = true;

        // Start our background thread
        threadStart();

        // Create the specified minimum number of processors
        while (curProcessors < minProcessors) {
            if ((maxProcessors > 0) && (curProcessors >= maxProcessors))
                break;
            HttpProcessor processor = newProcessor();
            recycle(processor);
        }

    }
    
private void threadStart() {
       
       log(sm.getString("httpConnector.starting"));

        thread = new Thread(this, threadName);
        thread.setDaemon(true);
        thread.start();

    }
    
void recycle(HttpProcessor processor) {
        processors.push(processor);

    }
```

stop方法：
  1. stop方法也是首先判断状态位，对外发布STOP_EVENT事件，同时重置started状态位
  2. 回收已经创建但未使用HttpProcessor
  3. 关闭ServerSocket，重置Stop状态位
```java
public void stop() throws LifecycleException {
    // Validate and update our current state
    if (!started)
      throw new LifecycleException
        (sm.getString("httpConnector.notStarted"));
    lifecycle.fireLifecycleEvent(STOP_EVENT, null);
    started = false;

    // Gracefully shut down all processors we have created
    for (int i = created.size() - 1; i >= 0; i--) {
      HttpProcessor processor = (HttpProcessor) created.elementAt(i);
      if (processor instanceof Lifecycle) {
        try {
          ((Lifecycle) processor).stop();
        } catch (LifecycleException e) {
          log("HttpConnector.stop", e);
        }
      }
    }

    synchronized (threadSync) {
      // Close the server socket we were using
      if (serverSocket != null) {
        try {
          serverSocket.close();
        } catch (IOException e) {
          ;
        }
      }
      // Stop our background thread
      threadStop();
    }
    serverSocket = null;
  }
  
 private void threadStop() {

        log(sm.getString("httpConnector.stopping"));

        stopped = true;
        try {
            threadSync.wait(5000);
        } catch (InterruptedException e) {
            ;
        }
        thread = null;

    }
```

#### HttpConnector的真实工作:实现的runnable接口

  1. 调用ServerSocket的accept()方法监听address地址的port端口。同时设置超时时间与TcpNoDelay
  2. `HttpConnector`实现了`Thread`的run方法。在run方法中开启使用 
    `socket = serverSocket.accept();`监听端口到来的请求。
  3. 当存在新的请求的时候, 通过 `createProcessor()`方法，创建一个HttpProcessor。HttpProcessor是HttpConnector移交request的下一个选手，每个request会对应一个HttpProcessor
  4. createProcessor方法保证了创建的Processor个数不会超过设置的参数：minProcessors与maxProcessors
  5. `processor.assign(socket);` 为分配的processor设置socket
```java
public void run() {
        // Loop until we receive a shutdown command
        //stopped 后台线程停止标志位
        while (!stopped) {
            // Accept the next incoming connection from the server socket
            Socket socket = null;
            try {
                //                if (debug >= 3)
                //                    log("run: Waiting on serverSocket.accept()");
                //循环监听socket，是否存在需求处理request
                //当没有request到来是，程序会阻塞在这里
                socket = serverSocket.accept();
                //                if (debug >= 3)
                //                    log("run: Returned from serverSocket.accept()");
                if (connectionTimeout > 0)
                    socket.setSoTimeout(connectionTimeout); //设置超时时间60000毫秒
                socket.setTcpNoDelay(tcpNoDelay);   //设置启用Negle算法
            } catch (AccessControlException ace) {
                log("socket accept security exception", ace);
                continue;
            } catch (IOException e) {
                //                if (debug >= 3)
                //                    log("run: Accept returned IOException", e);
                try {
                    // If reopening fails, exit
                    synchronized (threadSync) { //threadSync 线程同步object，没有其他作用
                        if (started && !stopped)
                            log("accept error: ", e);
                        if (!stopped) {
                            //                    if (debug >= 3)
                            //                        log("run: Closing server socket");
                            serverSocket.close();
                            //                        if (debug >= 3)
                            //                            log("run: Reopening server socket");
                            serverSocket = open();
                        }
                    }
                    //                    if (debug >= 3)
                    //                        log("run: IOException processing completed");
                } catch (IOException ioe) {
                    log("socket reopen, io problem: ", ioe);
                    break;
                } catch (KeyStoreException kse) {
                    log("socket reopen, keystore problem: ", kse);
                    break;
                } catch (NoSuchAlgorithmException nsae) {
                    log("socket reopen, keystore algorithm problem: ", nsae);
                    break;
                } catch (CertificateException ce) {
                    log("socket reopen, certificate problem: ", ce);
                    break;
                } catch (UnrecoverableKeyException uke) {
                    log("socket reopen, unrecoverable key: ", uke);
                    break;
                } catch (KeyManagementException kme) {
                    log("socket reopen, key management problem: ", kme);
                    break;
                }

                continue;
            }

            // Hand this socket off to an appropriate processor
            HttpProcessor processor = createProcessor();
            if (processor == null) {
                try {
                    log(sm.getString("httpConnector.noProcessor"));
                    socket.close();
                } catch (IOException e) {
                    ;
                }
                continue;
            }
            //            if (debug >= 3)
            //                log("run: Assigning socket to processor " + processor);
            processor.assign(socket);

            // The processor will recycle itself when it finishes

        }

        // Notify the threadStop() method that we have shut ourselves down
        //        if (debug >= 3)
        //            log("run: Notifying threadStop() that we have shut down");
        synchronized (threadSync) {
            threadSync.notifyAll();
        }

    }
    
private HttpProcessor createProcessor() {

        synchronized (processors) {

            //如果存在已经创建但是还没有被使用的processor
            if (processors.size() > 0) {
                // if (debug >= 2)
                // log("createProcessor: Reusing existing processor");
                return ((HttpProcessor) processors.pop());
            }
            //不存在没有被使用已经创建的processor,且没有达到最大的创建个数
            if ((maxProcessors > 0) && (curProcessors < maxProcessors)) {
                // if (debug >= 2)
                // log("createProcessor: Creating new processor");
                return (newProcessor());
            } else {
                if (maxProcessors < 0) {
                    // if (debug >= 2)
                    // log("createProcessor: Creating new processor");
                    return (newProcessor());
                } else {
                    // if (debug >= 2)
                    // log("createProcessor: Cannot create new processor");
                    return (null);
                }
            }
        }

    }
```

HttpConnector中真正创建Processor的创建方法：
1. 首先new一个新的Processor对象，分配给它一个id号（curProcessors）
2. 调用start，启动Processor的run方法里面的逻辑。里面会一直阻塞，直到Processor被调用assign方法被分配一个Socket
``` java
 private HttpProcessor newProcessor() {

        //        if (debug >= 2)
        //            log("newProcessor: Creating new processor");
        HttpProcessor processor = new HttpProcessor(this, curProcessors++);
        if (processor instanceof Lifecycle) {
            try {
                ((Lifecycle) processor).start();
            } catch (LifecycleException e) {
                log("newProcessor", e);
                return (null);
            }
        }
        //将processor放入到已经创建的collection中
        created.addElement(processor);
        return (processor);
    }
```

### HttpProcessor

#### HttpProcessor的作用

HttpProcessor主要是为了辅助HttpConnector，一个HttpConnector内部会维持一个Proccessor结合，以便能够及时处理请求。当一个Socket监听到一个Request的时候，会给这个Socket分配一个唯一的Processor来处理。它内部的很多参数来自于HttpConnector，HttpRequest与HttpResponse也是调用HttpConnector来创建的，中间Processor会将解析得到的数据填充到Request与Response中，并调用上层容器继续处理，最后通过在HttpConnector中将Response返回给Client


#### HttpProcessor的创建实例化

HttpProcessor的实例化是通过HttpConnetor来完成的，由HttpConnector获取的很多参数。其中最重要是通过调用HttpConnector的方法创建了Request与Response对象。

``` java

public HttpProcessor(HttpConnector connector, int id) {

        super();
        this.connector = connector;
        this.debug = connector.getDebug();
        this.id = id;
        this.proxyName = connector.getProxyName();
        this.proxyPort = connector.getProxyPort();
        this.request = (HttpRequestImpl) connector.createRequest();
        this.response = (HttpResponseImpl) connector.createResponse();
        this.serverPort = connector.getPort();
        this.threadName =
          "HttpProcessor[" + connector.getPort() + "][" + id + "]";

    }
```

#####HttpProcessor的参数解释：

```java
private boolean available = false;					//是否存在可用的新Socket
private HttpConnector connector = null;				//关联的HttpConnector
private int debug = 0;								//The debugging detail level for this component.
private int id = 0;									//该Processor的id，传入HttpConnector的参数curProcessors,代表当前已经创建的processor数量
private LifecycleSupport lifecycle = new LifecycleSupport(this); //生命周期管理
private static final String match =  ";" + Globals.SESSION_PARAMETER_NAME + "=";	
private static final char[] SESSION_ID = match.toCharArray();
private StringParser parser = new StringParser();	//StirngParser:用来解析requst的请求行
private String proxyName = null;					//对应HttpConnector的proxyName
private int proxyPort = 0;							//对应HttpConnector的proxyPort
private HttpRequestImpl request = null;				//Request的实例对象
private HttpResponseImpl response = null;			//Response的实例对象
private int serverPort = 0;							//对应HttpConnector的port
protected StringManager sm = StringManager.getManager(Constants.Package);	//日志用到的常量string properties管理
private Socket socket = null;						//Socket
private boolean started = false;					//该component是否启动
private boolean stopped = false;					//该component是否停止
private Thread thread = null;						//后台线程，这个将这个线程设置为了Daemon守护线程，防止程序终止。
private String threadName = null;					//threadName
private Object threadSync = new Object();			//线程同步对象
private boolean keepAlive = false;					//Socket的请求，是否保持长连接
private boolean http11 = true;						//request是否是HTTP/1.1
private boolean sendAck = false;					//发送ACK确认帧，http 1.1协议时使用
private static final byte[] ack = (new String("HTTP/1.1 100 Continue\r\n\r\n")).getBytes();
private static final byte[] CRLF = (new String("\r\n")).getBytes();
//private char[] lineBuffer = new char[4096];
private HttpRequestLine requestLine = new HttpRequestLine();//HttpRequestLine
private int status = Constants.PROCESSOR_IDLE;		//Prcessor的状态：PROCESSOR_IDLE = 0,PROCESSOR_ACTIVE = 1
```

HttpConnector在获取到可用HttpProcessor之后，会调用该assigin方法给proccess一个Socket用来处理后来的请求.
1. 赋值assign过来的socket
2. 阻塞自己的assign方法，防止被再次调用
3. notifyAll(), 唤醒阻塞的run方法
```java
synchronized void assign(Socket socket) {

        // Wait for the Processor to get the previous Socket
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        // Store the newly available Socket and notify our thread
        this.socket = socket;
        available = true;
        notifyAll();

        if ((debug >= 1) && (socket != null))
            log(" An incoming request is being assigned");

    }
```
#### HttpProcessor的启动与暂停
1. 启动：
  * 因为HttpProcessor使用Lifecycle来管理自己的生命周期，因此通过调用重写Lifecycyle的start()方法就能够启动
  * 启动过程中对外传递了START启动事件，同时设置守护线程，通过守护线程进行启动
```java

public void start() throws LifecycleException {

        if (started)
            throw new LifecycleException
                (sm.getString("httpProcessor.alreadyStarted"));
        lifecycle.fireLifecycleEvent(START_EVENT, null);
        started = true; //start启动标志位

        threadStart();

    }
    
private void threadStart() {

        log(sm.getString("httpProcessor.starting"));

        thread = new Thread(this, threadName);
        //守护线程，是指在程序运行的时候在后台提供一种通用服务的线程，比如垃圾回收线程就是一个很称职的守护者，并且这种线程并不属于程序中不可或缺的部分。
        // 因此，当所有的非守护线程结束时，程序也就终止了，同时会杀死进程中的所有守护线程。反过来说，只要任何非守护线程还在运行，程序就不会终止。
        thread.setDaemon(true);
        thread.start();

        if (debug >= 1)
            log(" Background thread has been started");

    }
```
2. 终止：

HttpProcessor的stop方法并没有真正的将Processor暂停。而是通过assing(null)方法，分配一个空的Socket，然后内部会通过NotifyAll方法唤醒阻塞的run方法。然后会通过await方法阻塞Process，直到通过assigin方法，分配到一个可用的Socket

``` java
public void stop() throws LifecycleException {

        if (!started)
            throw new LifecycleException
                (sm.getString("httpProcessor.notStarted"));
        lifecycle.fireLifecycleEvent(STOP_EVENT, null);
        started = false;

        threadStop();

    }  
   
private void threadStop() {

        log(sm.getString("httpProcessor.stopping"));

        stopped = true;
        assign(null);

        if (status != Constants.PROCESSOR_IDLE) {
            // Only wait if the processor is actually processing a command
            synchronized (threadSync) {
                try {
                    threadSync.wait(5000);
                } catch (InterruptedException e) {
                    ;
                }
            }
        }
        thread = null;

    }
```

#### HttpProcessor的主要逻辑run()方法
1. 通过await方法阻塞run方法，直到被分配一个可用的 Socket
2. 通过process方法处理Socket中的ruqest请求
3. 处理完成通过HttpConnector的recycle方法回收该Processor(放入已经创建但未被使用的集合中)。同时唤醒阻塞的assign方法

``` java
public void run() {

        // Process requests until we receive a shutdown signal
        while (!stopped) {

            // Wait for the next socket to be assigned
            Socket socket = await();
            if (socket == null)
                continue;

            // Process the request from this socket
            try {
                process(socket);
            } catch (Throwable t) {
                log("process.invoke", t);
            }

            // Finish up this request
            connector.recycle(this);
        }

        // Tell threadStop() we have shut ourselves down successfully
        synchronized (threadSync) {
            threadSync.notifyAll();
        }
    }
    
    
private synchronized Socket await() {

        // Wait for the Connector to provide a new Socket
        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        // Notify the Connector that we have received this Socket
        Socket socket = this.socket;
        available = false;
        notifyAll();

        if ((debug >= 1) && (socket != null))
            log("  The incoming request has been awaited");

        return (socket);

    }
```

真正处理请求的地方：process(socket)
1. 获取Socket中的inputStream与outputStream,分别入到Request与Response中，同时通过解析Socket，inputStream，outputSteam来填充构建的Request与response
2. 调用下面的代码将后序的处理交给顶层的Container中，调用流程如上面的架构图：HttpConnector--》Engine--》Host--》Context--》Wrapper
`connector.getContainer().invoke(request, response);`
3. 在Request与Response在经过上层的各个容器之后，会被填充各种数据
4. response.finishResponse(); 返回Response数据，内部调用了flush方法，刷新缓冲区内容的数据通过Socket到Client

``` java

private void process(Socket socket) {
        boolean ok = true;   //处理过程是否正常
        boolean finishResponse = true;   //是否完成Response
        SocketInputStream input = null; //Socket的输入流,将会由这里面解析各种数据填充到Request与Response中
        OutputStream output = null;     //Socket的输出流,完成Response之后，写入缓冲区的数据会由Socket发送到client

        // Construct and initialize the objects we will need
        try {
            //通过Socket获取输入流SocketInputStream
            input = new SocketInputStream(socket.getInputStream(),
                                          connector.getBufferSize());
        } catch (Exception e) {
            log("process.create", e);
            ok = false;
        }

        keepAlive = true;

        while (!stopped && ok && keepAlive) {

            finishResponse = true;

            try {
                request.setStream(input);
                request.setResponse(response);
                output = socket.getOutputStream();
                response.setStream(output);
                response.setRequest(request);
                ((HttpServletResponse) response.getResponse()).setHeader
                    ("Server", SERVER_INFO);
            } catch (Exception e) {
                log("process.create", e);
                ok = false;
            }


            // Parse the incoming request
            try {
                if (ok) {

                    //由Socket记录到记录Server Port与 Inet参数与Socket到request中
                    parseConnection(socket);
                    //解析请求行，同时设置各种参数到Request与Response中
                    parseRequest(input, output);
                    if (!request.getRequest().getProtocol()
                        .startsWith("HTTP/0"))

                        //解析请求头，同时设置各种解析的数据到Request与Response中国年
                        parseHeaders(input);
                    //如果是Http/1.1,发送ACK确认帧
                    if (http11) {
                        // Sending a request acknowledge back to the client if
                        // requested.
                        ackRequest(output);
                        // If the protocol is HTTP/1.1, chunking is allowed.
                        if (connector.isChunkingAllowed())
                            response.setAllowChunking(true);
                    }

                }
            } catch (EOFException e) {
                // It's very likely to be a socket disconnect on either the
                // client or the server
                ok = false;
                finishResponse = false;
            } catch (ServletException e) {
                ok = false;
                try {
                    ((HttpServletResponse) response.getResponse())
                        .sendError(HttpServletResponse.SC_BAD_REQUEST);
                } catch (Exception f) {
                    ;
                }
            } catch (InterruptedIOException e) {
                if (debug > 1) {
                    try {
                        log("process.parse", e);
                        ((HttpServletResponse) response.getResponse())
                            .sendError(HttpServletResponse.SC_BAD_REQUEST);
                    } catch (Exception f) {
                        ;
                    }
                }
                ok = false;
            } catch (Exception e) {
                try {
                    log("process.parse", e);
                    ((HttpServletResponse) response.getResponse()).sendError
                        (HttpServletResponse.SC_BAD_REQUEST);
                } catch (Exception f) {
                    ;
                }
                ok = false;
            }

            // Ask our Container to process this request
            try {
                ((HttpServletResponse) response).setHeader
                    ("Date", FastHttpDateFormat.getCurrentDate());
                if (ok) {
                    //交接给顶层Container处理
                    //HttpProcessor接管Socket，由里面的Stream创建出request,response
                    //并解析请求头，为request中各个项赋值
                    //调用顶层的Container继续处理Request，response
                    connector.getContainer().invoke(request, response);
                }
            } catch (ServletException e) {
                log("process.invoke", e);
                try {
                    ((HttpServletResponse) response.getResponse()).sendError
                        (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                } catch (Exception f) {
                    ;
                }
                ok = false;
            } catch (InterruptedIOException e) {
                ok = false;
            } catch (Throwable e) {
                log("process.invoke", e);
                try {
                    ((HttpServletResponse) response.getResponse()).sendError
                        (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                } catch (Exception f) {
                    ;
                }
                ok = false;
            }

            // Finish up the handling of the request
            if (finishResponse) {
                try {
                    //关闭输出流等相关操作,会通过flush方法将缓存区的数据强制发送出去，而不用等到缓存区完全填满
                    //这里也是Socket完成response请求，返回给客户端的地方
                    response.finishResponse();
                } catch (IOException e) {
                    ok = false;
                } catch (Throwable e) {
                    log("process.invoke", e);
                    ok = false;
                }
                try {
                    //清空request
                    request.finishRequest();
                } catch (IOException e) {
                    ok = false;
                } catch (Throwable e) {
                    log("process.invoke", e);
                    ok = false;
                }
                try {
                    if (output != null)
                        output.flush();
                } catch (IOException e) {
                    ok = false;
                }
            }

            // We have to check if the connection closure has been requested
            // by the application or the response stream (in case of HTTP/1.0
            // and keep-alive).
            if ( "close".equals(response.getHeader("Connection")) ) {
                keepAlive = false;
            }

            // End of request processing
            status = Constants.PROCESSOR_IDLE;

            // Recycling the request and the response objects
            request.recycle();
            response.recycle();

        }

        try {
            //清空Socket中的input stream
            shutdownInput(input);
            socket.close();
        } catch (IOException e) {
            ;
        } catch (Throwable e) {
            log("process.invoke", e);
        }
        socket = null;

    }
```



附录：

1. [当我们在读写Socket时，我们究竟在读写什么？](https://zhuanlan.zhihu.com/p/38637493?utm_source=qq&utm_medium=social&utm_oi=44063118065664)

