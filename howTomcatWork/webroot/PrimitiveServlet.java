import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Create by haifei on 12/7/2018.
 */
public class PrimitiveServlet implements Servlet {

  public void init(ServletConfig servletConfig) throws ServletException {
    System.out.println("init");
  }

  public ServletConfig getServletConfig() {
    return null;
  }

  public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
    System.out.println("from service.");
    PrintWriter out = servletResponse.getWriter();
    out.println("Hello. Roses are red.");
    out.print("Violets are blue.");
  }

  public String getServletInfo() {
    return null;
  }

  public void destroy() {
    System.out.println("destroy");
  }
}
