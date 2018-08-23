package com.wisely.highlight_spring4.ch2.prepost;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Create by haifei on 23/8/2018.
 */
public class JSR250WayService {


  //在构造函数之后执行
  @PostConstruct
  public void init() {
    System.out.println("jsr250-init-method");
  }

  public JSR250WayService() {
    super();
    System.out.println("初始化构造函数- JSR250WayService");
  }

  //bean销毁之前执行
  @PreDestroy
  public void destroy() {
    System.out.println("jsr250-destroy-method");
  }
}
