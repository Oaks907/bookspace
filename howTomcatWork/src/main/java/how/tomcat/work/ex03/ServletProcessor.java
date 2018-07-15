package how.tomcat.work.ex03;

import how.tomcat.work.ex03.connector.http.Constants;
import how.tomcat.work.ex03.connector.http.HttpRequest;
import how.tomcat.work.ex03.connector.http.HttpRequestFacade;
import how.tomcat.work.ex03.connector.http.HttpResponse;
import how.tomcat.work.ex03.connector.http.HttpResponseFacade;

import javax.servlet.Servlet;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Create by haifei on 14/7/2018.
 */
public class ServletProcessor {

  public void process(HttpRequest request, HttpResponse response) {

    String uri = request.getRequestURI();
    String servletName = uri.substring(uri.lastIndexOf("/") + 1);
    URLClassLoader loader = null;

    try{
      URL[] urls = new URL[1];
      URLStreamHandler streamHandler = null;
      File classPath = new File(Constants.WEB_ROOT);
      String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
      urls[0] = new URL(null, repository, streamHandler);
      loader = new URLClassLoader(urls);
    } catch (Exception e) {
      System.out.println(e.toString());
    }

    Class myClass = null;
    try {
      System.out.println("servletName:" + servletName);
      myClass = loader.loadClass(servletName);
    } catch (Exception e) {
      System.out.println(e.toString());
    }

    Servlet servlet = null;

    try {
      servlet = (Servlet) myClass.newInstance();
      HttpRequestFacade requestFacade = new HttpRequestFacade(request);
      HttpResponseFacade responseFacade = new HttpResponseFacade(response);
      servlet.service(requestFacade, responseFacade);
      ((HttpResponse)response).finishResponse();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

















