package DesignPattern.createmode.SingletonMode;

import sun.security.jca.GetInstance;

/**
 * Create by haifei on 21/12/2018 11:38 AM.
 */
public class StaticSingleton {

  private StaticSingleton() {

  }

  public static StaticSingleton getInstance() {
    return SingletonHolder.SINGLETON;
  }

  private static class SingletonHolder {
    private static final StaticSingleton SINGLETON = new StaticSingleton();
  }
}
