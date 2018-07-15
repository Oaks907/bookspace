package how.tomcat.work.ex04.startup;

import how.tomcat.work.ex04.core.SimpleContainer;
import org.apache.catalina.connector.http.HttpConnector;

/**
 * Create by haifei on 15/7/2018.
 */
public class Bootstrap {

  public static void main(String[] args) {
    HttpConnector connector = new HttpConnector();
    SimpleContainer container = new SimpleContainer();
    connector.setContainer(container);
    try {
      connector.initialize();
      connector.start();
      System.in.read();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
