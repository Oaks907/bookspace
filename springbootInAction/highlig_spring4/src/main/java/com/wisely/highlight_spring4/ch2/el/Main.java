package com.wisely.highlight_spring4.ch2.el;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Create by haifei on 22/8/2018.
 */
public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EIConfig.class);

    EIConfig eiConfig = context.getBean(EIConfig.class);

    eiConfig.outputResource();

    context.close();
  }
}
