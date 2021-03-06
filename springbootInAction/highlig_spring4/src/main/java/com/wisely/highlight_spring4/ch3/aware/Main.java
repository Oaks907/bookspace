package com.wisely.highlight_spring4.ch3.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Create by haifei on 24/8/2018.
 */
public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig.class);

    AwareService awareService = context.getBean(AwareService.class);

    awareService.outputResult();

    context.close();
  }
}
