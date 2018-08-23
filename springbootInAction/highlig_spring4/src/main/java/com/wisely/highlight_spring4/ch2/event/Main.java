package com.wisely.highlight_spring4.ch2.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Create by haifei on 23/8/2018.
 */
public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);

    DemoPublisher demoPublisher = context.getBean(DemoPublisher.class);

    demoPublisher.publish("hello application event");

    context.close();
  }
}
