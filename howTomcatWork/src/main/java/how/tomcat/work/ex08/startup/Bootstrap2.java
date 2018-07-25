package how.tomcat.work.ex08.startup;

import how.tomcat.work.ex05.valves.ClientIPLoggerValve;
import how.tomcat.work.ex05.valves.HeaderLoggerValve;
import how.tomcat.work.ex08.core.SimpleContext;
import how.tomcat.work.ex08.core.SimpleContextLifecycleListener;
import how.tomcat.work.ex08.core.SimpleContextMapper;
import how.tomcat.work.ex08.core.SimpleLoader;
import how.tomcat.work.ex08.core.SimpleWrapper;
import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Loader;
import org.apache.catalina.Mapper;
import org.apache.catalina.Pipeline;
import org.apache.catalina.Valve;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.http.HttpConnector;
import org.apache.catalina.logger.FileLogger;

/**
 * Create by haifei on 20/7/2018.
 */
public class Bootstrap2 {

  public static void main(String[] args) {
    HttpConnector httpConnector = new HttpConnector();
    Wrapper wrapper1 = new SimpleWrapper();
    wrapper1.setName("Primitive");
    wrapper1.setServletClass("PrimitiveServlet");
    Wrapper wrapper2 = new SimpleWrapper();
    wrapper2.setName("Modern");
    wrapper2.setServletClass("ModernServlet");

    Context context = new SimpleContext();
    context.addChild(wrapper1);
    context.addChild(wrapper2);

    Valve valve1 = new HeaderLoggerValve();
    Valve valve2 = new ClientIPLoggerValve();

    ((Pipeline) context).addValve(valve1);
    ((Pipeline) context).addValve(valve2);

    Mapper mapper = new SimpleContextMapper();
    mapper.setProtocol("http");

    LifecycleListener listener = new SimpleContextLifecycleListener();
    ((Lifecycle) context).addLifecycleListener(listener);

    context.addMapper(mapper);
    Loader loader = new SimpleLoader();
    context.setLoader(loader);
    context.addServletMapping("/Primitive", "Primitive");
    context.addServletMapping("/Modern", "Modern");

    // ------ add logger ----------
    System.setProperty("catalina.base", System.getProperty("user.dir"));
    FileLogger logger = new FileLogger();
    logger.setPrefix("FileLog_");
    logger.setSuffix(".txt");
    logger.setTimestamp(true);
    logger.setDirectory("webroot");
    context.setLogger(logger);
    //--------------------------------

    httpConnector.setContainer(context);

    try {
      httpConnector.initialize();
      ((Lifecycle) httpConnector).start();
      ((Lifecycle) context).start();

      System.in.read();
      ((Lifecycle) context).stop();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
