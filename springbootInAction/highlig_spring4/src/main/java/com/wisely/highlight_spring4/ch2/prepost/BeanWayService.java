package com.wisely.highlight_spring4.ch2.prepost;

/**
 * Create by haifei on 23/8/2018.
 */
public class BeanWayService {

  public void init() {
    System.out.println("@bean-init-method");
  }

  public BeanWayService() {
    super();
    System.out.println("初始化构造函数- BeanWayService");
  }

  public void destroy() {
    System.out.println("@bean-destroy-method");
  }

}
