package com.wisely.highlight_spring4.ch2.prepost;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Create by haifei on 23/8/2018.
 */
public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrepostConfig.class);

    BeanWayService beanWayService = context.getBean(BeanWayService.class);

    JSR250WayService jsr250WayService = context.getBean(JSR250WayService.class);

    context.close();
  }
}
