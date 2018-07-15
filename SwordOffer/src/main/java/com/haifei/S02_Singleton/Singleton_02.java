package com.haifei.S02_Singleton;

/**
 * Create by haifei on 24/6/2018.
 * 懒汉式
 * 为了解决多个线程冲突的问题，初步引入了线程同步,但是他并不高效
 */
public class Singleton_02 {
  private static Singleton_02 instance;

  private Singleton_02() {

  }

  public static synchronized Singleton_02 getInstance() {
      if (null == instance) {
        instance = new Singleton_02();
      }
      return instance;
  }
}
