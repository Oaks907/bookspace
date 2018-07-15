package com.haifei.S02_Singleton;

/**
 * Create by haifei on 24/6/2018.
 * 静态内部类形式
 */
public class Singleton_05 {

  private Singleton_05() {

  }

  private static class SingletonHolder {
    private static final Singleton_05 instance = new Singleton_05();
  }

  private static Singleton_05 getInstance() {
    return SingletonHolder.instance;
  }
}
