package com.wisely.highlight_spring4.ch2.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Create by haifei on 22/8/2018.
 */
public class Main {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig.class);

    DemoPrototypeService prototypeService1 = context.getBean(DemoPrototypeService.class);
    DemoPrototypeService prototypeService2 = context.getBean(DemoPrototypeService.class);

    DemoSingletonService singletonService1 = context.getBean(DemoSingletonService.class);
    DemoSingletonService singletonService2 = context.getBean(DemoSingletonService.class);

    System.out.println("prototypeService是否相等：" + (prototypeService1 == prototypeService2));
    System.out.println("singletonService是否相等：" + (singletonService1 == singletonService2));

    context.close();
  }
}
