package DesignPattern.createmode.SingletonMode;

/**
 * Create by haifei on 21/12/2018 11:22 AM.
 * 懒汉模式的单例
 *
 */
public class SlackerSingleton {

  private static SlackerSingleton slackerSingleton = null;

  private SlackerSingleton() {

  }

  public static synchronized SlackerSingleton getInstance() {
    if (null == slackerSingleton) {
      slackerSingleton = new SlackerSingleton();
    }
    return slackerSingleton;
  }
}
