[TOC]

本篇介绍了StandardHost中使用的三个Valve：StandardHostValve，ErrorReportValve，ErrorDispatcherValve

## StandardHostValve

StandardHostValve的核心就一个invoke方法。里面就是根据Request匹配Context，并将Request传递给匹配到的Context。

```java
public void invoke(Request request, Response response, ValveContext valveContext)
        throws IOException, ServletException {
        if (!(request.getRequest() instanceof HttpServletRequest) ||
            !(response.getResponse() instanceof HttpServletResponse)) {
            return;     
        }
        //选择一个Context子容器继续处理Request
        StandardHost host = (StandardHost) getContainer();
        Context context = (Context) host.map(request, true);
        if (context == null) {
            ((HttpServletResponse) response.getResponse()).sendError
                (HttpServletResponse.SC_INTERNAL_SERVER_ERROR,sm.getString("standardHost.noContext"));
            return;
        }
        // 为当前线程绑定ClassLoader
        Thread.currentThread().setContextClassLoader
            (context.getLoader().getClassLoader());
        //更新session最后的访问时间
        HttpServletRequest hreq = (HttpServletRequest) request.getRequest();
        String sessionId = hreq.getRequestedSessionId();
        if (sessionId != null) {
            Manager manager = context.getManager();
            if (manager != null) {
                Session session = manager.findSession(sessionId);
                if ((session != null) && session.isValid())
                    session.access();
            }
        }
        // 请求context继续处理Request
        context.invoke(request, response);
    }
```


## ErrorReportValve

* 一般工作在Host上的Valve，当关联Context时，也能够正常工作
* 当ErrorReportValve在Response没有状态码且已经写入Header才不会继续向下调用

Valve的核心方法invoke：
* invoke(Request request, Response response,ValveContext context)
``` java
public void invoke(Request request, Response response, ValveContext context)
        throws IOException, ServletException {

        //调用ValveContext中的InvokeNext方法，即先执行下一个Valve
        context.invokeNext(request, response);

        ServletRequest sreq = (ServletRequest) request;
        Throwable throwable = (Throwable) sreq.getAttribute(Globals.EXCEPTION_ATTR);

        ServletResponse sresp = (ServletResponse) response;
        
        //commit状态代表response已经有status code且被写入了header
        if (sresp.isCommitted()) {
            return;
        }

        if (throwable != null) {
            // The response is an error
            response.setError();
            //重置response
            try {
                sresp.reset();
            } catch (IllegalStateException e) {
            }

            ServletResponse sresponse = (ServletResponse) response;
            if (sresponse instanceof HttpServletResponse)
                ((HttpServletResponse) sresponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }
		//设置暂停标志位位false
        response.setSuspended(false);
        try {
            //返回错误页面
            report(request, response, throwable);
        } catch (Throwable tt) {
            tt.printStackTrace();
        }
    }
```

* report方法是将response中的错误信息拼接为html，设置到response中

```java
protected void report(Request request, Response response, Throwable throwable) throws IOException {
        
        //不是HttpResponse直接返回
        if (!(response instanceof HttpResponse))
            return;
        HttpResponse hresponse = (HttpResponse) response;
        //不是HttpServletResponse直接返回
        if (!(response instanceof HttpServletResponse))
            return;
        HttpServletResponse hres = (HttpServletResponse) response;
        int statusCode = hresponse.getStatus();
        String message = RequestUtil.filter(hresponse.getMessage());
        if (message == null)
            message = "";

        if (statusCode < 300)
            return;
        if (statusCode == HttpServletResponse.SC_NOT_MODIFIED)
            return;

        //对于异常的处理
        Throwable rootCause = null;

        if (throwable != null) {

            if (throwable instanceof ServletException)
                rootCause = ((ServletException) throwable).getRootCause();

        }

        // Do nothing if there is no report for the specified status code
        String report = null;
        try {
            report = sm.getString("http." + statusCode, message);
        } catch (Throwable t) {
            ;
        }
        if (report == null)
            return;

        StringBuffer sb = new StringBuffer();

        sb.append("<html><head><title>");
        sb.append(ServerInfo.getServerInfo()).append(" - ");
        sb.append(sm.getString("errorReportValve.errorReport"));
        sb.append("</title>");
        sb.append("<STYLE><!--");
        sb.append("H1{font-family : sans-serif,Arial,Tahoma;color : white;background-color : #0086b2;} ");
        sb.append("H3{font-family : sans-serif,Arial,Tahoma;color : white;background-color : #0086b2;} ");
        sb.append("BODY{font-family : sans-serif,Arial,Tahoma;color : black;background-color : white;} ");
        sb.append("B{color : white;background-color : #0086b2;} ");
        sb.append("HR{color : #0086b2;} ");
        sb.append("--></STYLE> ");
        sb.append("</head><body>");
        sb.append("<h1>");
        sb.append(sm.getString("errorReportValve.statusHeader",
                               "" + statusCode, message)).append("</h1>");
        sb.append("<HR size=\"1\" noshade>");
        sb.append("<p><b>type</b> ");
        if (throwable != null) {
            sb.append(sm.getString("errorReportValve.exceptionReport"));
        } else {
            sb.append(sm.getString("errorReportValve.statusReport"));
        }
        sb.append("</p>");
        sb.append("<p><b>");
        sb.append(sm.getString("errorReportValve.message"));
        sb.append("</b> <u>");
        sb.append(message).append("</u></p>");
        sb.append("<p><b>");
        sb.append(sm.getString("errorReportValve.description"));
        sb.append("</b> <u>");
        sb.append(report);
        sb.append("</u></p>");

        if (throwable != null) {
            StringWriter stackTrace = new StringWriter();
            throwable.printStackTrace(new PrintWriter(stackTrace));
            sb.append("<p><b>");
            sb.append(sm.getString("errorReportValve.exception"));
            sb.append("</b> <pre>");
            sb.append(stackTrace.toString());
            sb.append("</pre></p>");
            if (rootCause != null) {
                stackTrace = new StringWriter();
                rootCause.printStackTrace(new PrintWriter(stackTrace));
                sb.append("<p><b>");
                sb.append(sm.getString("errorReportValve.rootCause"));
                sb.append("</b> <pre>");
                sb.append(stackTrace.toString());
                sb.append("</pre></p>");
            }
        }

        sb.append("<HR size=\"1\" noshade>");
        sb.append("<h3>").append(ServerInfo.getServerInfo()).append("</h3>");
        sb.append("</body></html>");

        //向response中写入生成的report数据
        try {
            Writer writer = response.getReporter();

            if (writer != null) {

                Locale locale = Locale.getDefault();
                try {
                    hres.setContentType("text/html");
                    hres.setLocale(locale);
                } catch (Throwable t) {
                    if (debug >= 1)
                        log("status.setContentType", t);
                }
                writer.write(sb.toString());
                writer.flush();
            }
        } catch (IOException e) {
            ;
        } catch (IllegalStateException e) {
            ;
        }
    }
```

## ErrorDispatcherValve

ErrorDispatcherValve阀主要是根据异常状态和状态码，去匹配合适的ErrorPage进行重定向。

* invoke(Request request, Response response, ValveContext context)

```java
public void invoke(Request request, Response response,
                       ValveContext context)
        throws IOException, ServletException {

        // 通过ValveContext先调用下一个Valve
        context.invokeNext(request, response);

        response.setSuspended(false);

        ServletRequest sreq = request.getRequest();
        Throwable t = (Throwable) sreq.getAttribute(Globals.EXCEPTION_ATTR);

        if (t != null) {
            throwable(request, response, t);
        } else {
            status(request, response);
        }

    }
```
invoke方法会根据Request中参数中的EXCEPTION_ATTR值，来获取到Throwable对象。
如果存在Throwable对象，就进入异常处理流程。否者进入到状态码处理流程。

* void throwable(Request request, Response response, Throwable throwable)
``` java
protected void throwable(Request request, Response response, Throwable throwable) {
        Context context = request.getContext();
        if (context == null)
            return;

        //异常信息
        Throwable realError = throwable;
        
        if (realError instanceof ServletException) {
            realError = ((ServletException) realError).getRootCause();
            if (realError == null) {
                realError = throwable;
            }
        } 
            
        //查找异常对应的ErrorPage
        ErrorPage errorPage = findErrorPage(context, realError);

        //当找到errorPage时，设置Request与Response的各种参数
        if (errorPage != null) {
            response.setAppCommitted(false);

            ServletRequest sreq = request.getRequest();
            ServletResponse sresp = response.getResponse();
            sreq.setAttribute(Globals.STATUS_CODE_ATTR, new Integer(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
            sreq.setAttribute(Globals.ERROR_MESSAGE_ATTR, throwable.getMessage());
            sreq.setAttribute(Globals.EXCEPTION_ATTR, realError);
            Wrapper wrapper = request.getWrapper();

            if (wrapper != null)
                sreq.setAttribute(Globals.SERVLET_NAME_ATTR, wrapper.getName());

            if (sreq instanceof HttpServletRequest)
                sreq.setAttribute(Globals.EXCEPTION_PAGE_ATTR, ((HttpServletRequest) sreq).getRequestURI());

            sreq.setAttribute(Globals.EXCEPTION_TYPE_ATTR, realError.getClass());

            //重要！通过重定向到ErrorPage页面处理status code与exception
            if (custom(request, response, errorPage)) {
                try {
                    sresp.flushBuffer();
                } catch (IOException e) {
                    log("Exception Processing " + errorPage, e);
                }
            }
        }
    }
```

ErrorDispatcherValve中的findErrorPage方法，会先根据异常获取到类名，然后到Context中查找对应的ErrorPage。
当前类名查找不到，就查找向上查找，查找父类。

* ErrorPage findErrorPage(Context context, Throwable exception)

``` java
protected static ErrorPage findErrorPage(Context context, Throwable exception) {
        if (exception == null)
            return (null);
        //获取的异常的Class，通过class的name去查找errorPage。
        Class clazz = exception.getClass();
        String name = clazz.getName();
        while (!"java.lang.Object".equals(clazz)) {
            //通过Context获取到指定的ErrorPage
            ErrorPage errorPage = context.findErrorPage(name);
            if (errorPage != null)
                return (errorPage);
            clazz = clazz.getSuperclass();
            if (clazz == null)
                break;
            name = clazz.getName();
        }
        return (null);
    }
```

status方法主要是根据Response的状态码，来查找对应的ErrorPage
* void status(Request request, Response response)

```java
protected void status(Request request, Response response) {
        if (!(response instanceof HttpResponse))
            return;
        HttpResponse hresponse = (HttpResponse) response;
        if (!(response.getResponse() instanceof HttpServletResponse))
            return;
        int statusCode = hresponse.getStatus();
        String message = RequestUtil.filter(hresponse.getMessage());
        if (message == null)
            message = "";

        //获取一个处理该status code 的通用error page
        Context context = request.getContext();
        if (context == null)
            return;

        ErrorPage errorPage = context.findErrorPage(statusCode);
        if (errorPage != null) {
            response.setAppCommitted(false);
            ServletRequest sreq = request.getRequest();
            ServletResponse sresp = response.getResponse();
            sreq.setAttribute(Globals.STATUS_CODE_ATTR,new Integer(statusCode));
            sreq.setAttribute(Globals.ERROR_MESSAGE_ATTR,message);

            //获取到request对应的Wrapper(servlet)
            Wrapper wrapper = request.getWrapper();
            if (wrapper != null)
                sreq.setAttribute(Globals.SERVLET_NAME_ATTR, wrapper.getName());
            if (sreq instanceof HttpServletRequest)
                sreq.setAttribute(Globals.EXCEPTION_PAGE_ATTR,((HttpServletRequest) sreq).getRequestURI());
            
            //重要！通过重定向到ErrorPage页面处理status code与exception
            if (custom(request, response, errorPage)) {
                try {
                    sresp.flushBuffer();
                } catch (IOException e) {
                    log("Exception Processing " + errorPage, e);
                }
            }
        }

    }
```

custom的作用主要是重定向到之前查找到的error page页面
* boolean custom(Request request, Response response, ErrorPage errorPage)

```java
protected boolean custom(Request request, Response response, ErrorPage errorPage) {
        if (!(request instanceof HttpRequest)) {
            return (false);     
        }
        HttpServletRequest hreq = (HttpServletRequest) request.getRequest();
        if (!(response instanceof HttpResponse)) {
            return (false);     
        }

        HttpServletResponse hres = (HttpServletResponse) response.getResponse();
        try {
            //resset response
            hres.reset();
            //重定向到指定位置的Error Page
            ServletContext servletContext = request.getContext().getServletContext();
            RequestDispatcher rd = servletContext.getRequestDispatcher(errorPage.getLocation());
            rd.forward(hreq, hres);
            //如果我们重定向，响应再次暂停
            response.setSuspended(false);
            return (true);
        } catch (Throwable t) {
            log("Exception Processing " + errorPage, t);
            return (false);
        }
    }
```