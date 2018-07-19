package how.tomcat.work.ex05.valves;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.Valve;
import org.apache.catalina.ValveContext;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Create by haifei on 17/7/2018.
 */
public class HeaderLoggerValve implements Valve, Contained{

  protected Container container;

  public Container getContainer() {
    return container;
  }

  public void setContainer(Container container) {
    this.container = container;
  }

  public String getInfo() {
    return null;
  }

  public void invoke(Request request, Response response, ValveContext context) throws IOException, ServletException {
    context.invokeNext(request, response);

    System.out.println("Header Logger Valve");
    ServletRequest serq = request.getRequest();
    if (serq instanceof HttpServletRequest) {
      HttpServletRequest hreq = (HttpServletRequest) serq;
      Enumeration headerNames = hreq.getHeaderNames();
      while (headerNames.hasMoreElements()) {
        String headName = headerNames.nextElement().toString();
        String headerValue = hreq.getHeader(headName);
        System.out.println(headName + ":" + headerValue);
      }
    } else {
      System.out.println("Not an HTTP Request");
    }
    System.out.println("-------------------------------------");
  }
}
