package DesignPattern.createmode.SingletonMode;

/**
 * Create by haifei on 21/12/2018 11:29 AM.
 * double check mode
 */
public class DCLSingleton {

  private static DCLSingleton dclSingleton = null;

  private DCLSingleton() {

  }

  public static DCLSingleton getInstance() {
    if (null == dclSingleton) {
      synchronized (DCLSingleton.class) {
        if (null == dclSingleton) {
          dclSingleton = new DCLSingleton();
        }
      }
    }
    return dclSingleton;
  }
}
