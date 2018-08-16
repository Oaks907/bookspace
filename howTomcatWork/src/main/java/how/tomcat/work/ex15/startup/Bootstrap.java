package how.tomcat.work.ex15.startup;

import org.apache.catalina.Connector;
import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Loader;
import org.apache.catalina.connector.http.HttpConnector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.startup.ContextConfig;

/**
 * Create by haifei on 16/8/2018.
 */
public class Bootstrap {

  public static void main(String[] args) {
    System.setProperty("catalina.base", System.getProperty("user.dir"));
    Connector connector = new HttpConnector();

    Context context = new StandardContext();
    context.setPath("/app1");
    context.setDocBase("app1");
    LifecycleListener listener = new ContextConfig();
    ((Lifecycle) context).addLifecycleListener(listener);

    Host host = new StandardHost();
    host.addChild(context);
    host.setAppBase("webapps");
    host.setName("localhost");

    Loader loader = new WebappLoader();
    context.setLoader(loader);
    connector.setContainer(host);

    try {
      connector.initialize();
      ((Lifecycle) connector).start();
      ((Lifecycle) host).start();
      Container[] containers = context.findChildren();
      for (int i = 0; i < containers.length; i++) {
        Container child = containers[i];
        System.out.println(child.getName());
      }

      System.in.read();
      ((Lifecycle) host).stop();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
