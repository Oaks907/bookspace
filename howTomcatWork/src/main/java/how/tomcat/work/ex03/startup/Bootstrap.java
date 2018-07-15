package how.tomcat.work.ex03.startup;

import how.tomcat.work.ex03.connector.http.HttpConnector;

import java.util.Locale;

/**
 * Create by haifei on 14/7/2018.
 */
public class Bootstrap {

  public static void main(String[] args) {
    HttpConnector httpConnector = new HttpConnector();

    httpConnector.start();
  }
}
