package how.tomcat.work.ex05.core;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.HttpRequest;
import org.apache.catalina.HttpResponse;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.Valve;
import org.apache.catalina.ValveContext;
import org.apache.catalina.Wrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLDocument;
import java.io.IOException;

/**
 * Create by haifei on 20/7/2018.
 */
public class SimpleContextValve implements Valve, Contained {

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

  public void invoke(Request request, Response response, ValveContext valveContext) throws IOException,
    ServletException {
    if (!(request.getRequest() instanceof HttpServletRequest) ||
      !(response.getResponse() instanceof HttpServletResponse)) {
      return;
    }

    HttpServletRequest hreq = (HttpServletRequest) request.getRequest();
    String contextPath = hreq.getContextPath();
    String requestURI = ((HttpRequest) request).getDecodedRequestURI();
    String relativeURI = requestURI.substring(contextPath.length()).toUpperCase();

    Context context = (Context) getContainer();
    Wrapper wrapper = null;
    try {
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
    wrapper.invoke(request, response);
  }

  private void badRequest(String requestURI, HttpServletResponse response) {
    try {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, requestURI);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void notFound(String reqeustURI, HttpServletResponse response) {
    try {
      response.sendError(HttpServletResponse.SC_NOT_FOUND, reqeustURI);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
