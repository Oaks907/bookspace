package how.tomcat.work.ex09.startup;

import how.tomcat.work.ex09.core.SimpleContextConfig;
import how.tomcat.work.ex09.core.SimpleWrapper;
import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Loader;
import org.apache.catalina.Manager;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.http.HttpConnector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.session.StandardManager;

/**
 * Create by haifei on 20/7/2018.
 */
public class Bootstrap {

  public static void main(String[] args) {
    System.setProperty("catalina.base", System.getProperty("user.dir"));
    HttpConnector httpConnector = new HttpConnector();
    Wrapper wrapper1 = new SimpleWrapper();
    wrapper1.setName("Session");
    wrapper1.setServletClass("SessionServlet");

    Context context = new StandardContext();
    context.setPath("/myApp");
    context.setDocBase("myApp");

    context.addChild(wrapper1);

    context.addServletMapping("/myApp/Session", "Session");

    LifecycleListener listener =  new SimpleContextConfig();
    ((Lifecycle) context).addLifecycleListener(listener);

    Loader loader = new WebappLoader();
    context.setLoader(loader);

    httpConnector.setContainer(context);

    Manager manager = new StandardManager();
    context.setManager(manager);

    try {
      httpConnector.initialize();
      ((Lifecycle) httpConnector).start();
      ((Lifecycle) context).start();

      System.in.read();
      ((Lifecycle)context).stop();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
