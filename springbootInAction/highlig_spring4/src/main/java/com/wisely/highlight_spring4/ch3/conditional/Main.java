package com.wisely.highlight_spring4.ch3.conditional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Create by haifei on 25/8/2018.
 */
public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Conditional.class);

    ListService service = context.getBean(ListService.class);
    System.out.println(context.getEnvironment().getProperty("os.name") + "系统下的列表命令为：" + service.showListCmd());

    context.close();
  }
}
