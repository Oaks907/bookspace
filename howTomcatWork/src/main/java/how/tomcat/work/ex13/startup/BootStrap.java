package how.tomcat.work.ex13.startup;

import how.tomcat.work.ex13.core.SimpleContextConfig;
import org.apache.catalina.Connector;
import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Loader;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.http.HttpConnector;
import org.apache.catalina.core.ContainerBase;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardWrapper;
import org.apache.catalina.loader.WebappLoader;

/**
 * Create by haifei on 15/8/2018.
 */
public class BootStrap {

  public static void main(String[] args) {
    System.setProperty("catalina.base", System.getProperty("user.dir"));
    Connector connector = new HttpConnector();

    Wrapper wrapper1 = new StandardWrapper();
    wrapper1.setName("Primitive");
    wrapper1.setServletClass("PrimitiveServlet");
    ((ContainerBase) wrapper1).setDebug(1);
    Wrapper wrapper2 = new StandardWrapper();
    wrapper2.setName("Modern");
    wrapper2.setServletClass("ModernServlet");
    ((ContainerBase) wrapper2).setDebug(1);

    Context context = new StandardContext();
    context.setPath("/app1");
    context.setDocBase("app1");
    context.addChild(wrapper1);
    context.addChild(wrapper2);
    ((ContainerBase) context).setDebug(1);

    LifecycleListener listener = new SimpleContextConfig();
    ((Lifecycle) context).addLifecycleListener(listener);

    Host host = new StandardHost();
    host.addChild(context);
    host.setName("localhost");
    host.setAppBase("webapps");
    ((ContainerBase) host).setDebug(1);


    Loader loader = new WebappLoader();
    context.setLoader(loader);
    context.addServletMapping("/Primitive", "Primitive");
    context.addServletMapping("/ModernServlet", "Modern");

    connector.setContainer(host);

    try {

      connector.initialize();
      ((Lifecycle) connector).start();
      ((Lifecycle) host).start();
      System.in.read();
      ((Lifecycle) host).stop();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
