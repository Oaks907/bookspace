package com.wisely.highlight_spring4.ch1.aop;

import org.springframework.stereotype.Service;

/**
 * Create by haifei on 21/8/2018.
 */
@Service
public class DemoAnnotationService {

  @Action(name = "注解式的拦截的add操作")
  public void add() {

  }
}
