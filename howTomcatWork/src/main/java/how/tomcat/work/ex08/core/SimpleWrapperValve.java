package how.tomcat.work.ex08.core;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.Valve;
import org.apache.catalina.ValveContext;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by haifei on 17/7/2018.
 * 作为Basic Wrapper，独立的servlet在Pipeline中首先完成
 */
public class SimpleWrapperValve implements Valve, Contained{

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

    //???
    SimpleWrapper wrapper = (SimpleWrapper) getContainer();
    ServletRequest sreq = request.getRequest();
    ServletResponse sres = response.getResponse();
    Servlet servlet = null;
    HttpServletRequest hreq = null;
    if (sreq instanceof  HttpServletRequest){
      hreq = (HttpServletRequest) sreq;
    }
    HttpServletResponse hres = null;
    if (sres instanceof HttpServletResponse) {
      hres = (HttpServletResponse) sres;
    }

    servlet = wrapper.allocate();
    if (hres != null && hreq != null) {
      servlet.service(hreq, hres);
    } else {
      servlet.service(sreq, sres);
    }
  }
}
