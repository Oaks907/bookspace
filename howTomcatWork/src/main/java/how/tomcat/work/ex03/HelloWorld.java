package how.tomcat.work.ex03;

import org.apache.catalina.util.StringManager;

import java.util.ResourceBundle;

/**
 * Create by haifei on 15/7/2018.
 */
public class HelloWorld {
  public static void main(String[] args) {
//    StringManager sm = StringManager.getManager("ex03.connector.http");
//    System.out.println(sm.getString("httpConnector.alreadyInitialized"));
    String str = getProperty("httpConnector.alreadyInitialized");
    System.out.println(str);
    System.out.println(resourceBundle.getLocale());
    System.out.println(resourceBundle.getKeys());

    StringManager sm = StringManager.getManager("how.tomcat.work.ex03.connector.http");
    System.out.println(sm.getString("httpConnector.alreadyInitialized"));
  }


  /**
   * Properties file name.
   */
  private static final String FILENAME = "LocalStrings";

  /**
   * Resource bundle.
   */
  private static ResourceBundle resourceBundle = ResourceBundle.getBundle(FILENAME);

  /**
   * Method to read the property value.
   *
   * @param key
   * @return
   */
  public static String getProperty(final String key) {
    String str = null;
    if (resourceBundle != null) {
      str = resourceBundle.getString(key);
      System.out.println(("Value found: " + str + " for key: " + key));
    } else {
      System.out.println(("Properties file was not loaded correctly!!"));
    }
    return str;
  }

}
