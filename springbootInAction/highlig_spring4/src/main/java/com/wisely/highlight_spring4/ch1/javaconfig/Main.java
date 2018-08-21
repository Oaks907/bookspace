package com.wisely.highlight_spring4.ch1.javaconfig;

import com.wisely.highlight_spring4.ch1.di.DIConfig;
import com.wisely.highlight_spring4.ch1.di.UserFunctionService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Create by haifei on 21/8/2018.
 */
public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfig.class);
    com.wisely.highlight_spring4.ch1.di.UserFunctionService userFunctionService = context.getBean(UserFunctionService.class);
    System.out.println(userFunctionService.sayHello("World"));

    context.close();
  }
}
