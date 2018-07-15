package com.haifei.S02_Singleton;

/**
 * Create by haifei on 24/6/2018.
 * 双重检查锁形式。
 * new Singleton_03(); 不是原子操作，其执行的操作是
 * 1. 给 instance 分配内存
 * 2. 调用 Singleton 的构造函数来初始化成员变量
 * 3. 将instance对象指向分配的内存空间（执行完这步 instance 就为非 null 了）
 *
 * 所以执行的顺序不变，可能为1-2-3，也可能是1-3-2。当为后者时，会告知另外的线程instance已经创建完毕了，
 * 会引起错误
 * 改进的方式是将instance声明为volatile类型,防止指令重排序
 */
public class Singleton_03 {
  private static Singleton_03 instance;
  //private volatile static Singleton_03 instance;

  private Singleton_03() {

  }

  public static Singleton_03 getInstance() {
    if (null == instance) {
      synchronized (Singleton_03.class) {
        if (null == instance) {
          instance = new Singleton_03();
        }
      }
    }
    return instance;
  }
}
