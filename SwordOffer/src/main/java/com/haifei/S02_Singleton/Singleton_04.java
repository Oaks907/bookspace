package com.haifei.S02_Singleton;

/**
 * Create by haifei on 24/6/2018.
 * 饿汉式
 * 在第一次加载类到内存中时就会初始化，所以创建实例本身是线程安全的。
 * 缺点是不是懒加载模式，单例会在类加载的时候创建，而不是等待getInstance方法调用时创建
 */
public class Singleton_04 {
  private static final Singleton_04 instance = new Singleton_04();

  private Singleton_04() {

  }

  public static Singleton_04 getInstance() {
    return instance;
  }
}
