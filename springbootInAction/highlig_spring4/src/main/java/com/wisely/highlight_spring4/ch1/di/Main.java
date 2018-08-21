package com.wisely.highlight_spring4.ch1.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Create by haifei on 21/8/2018.
 */
public class Main {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfig.class);
    UserFunctionService userFunctionService = context.getBean(UserFunctionService.class);
    System.out.println(userFunctionService.sayHello("World"));

    context.close();
  }
}
