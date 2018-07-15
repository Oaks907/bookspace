package com.haifei.S02_Singleton;

/**
 * Create by haifei on 24/6/2018.
 * 懒汉式
 * 多个线程不能正常工作
 */
public class Singleton_01 {
  private static Singleton_01 instance;

  private Singleton_01() {

  }

  public static Singleton_01 getInstance() {
    if (instance == null ) {
      instance = new Singleton_01();
    }
    return instance;
  }
}
