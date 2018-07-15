package how.tomcat.work.ex03.connector.http;

import java.io.File;

/**
 * Create by haifei on 14/7/2018.
 */
public class Constants {
  public static final String WEB_ROOT =
    System.getProperty("user.dir") + File.separator  + "webroot";
  public static final String Package = "how.tomcat.work.ex03.connector.http";
  public static final int DEFAULT_CONNECTION_TIMEOUT = 60000;
  public static final int PROCESSOR_IDLE = 0;
  public static final int PROCESSOR_ACTIVE = 1;
}
