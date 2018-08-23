package com.wisely.highlight_spring4.ch2.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Create by haifei on 23/8/2018.
 */
public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.getEnvironment().setActiveProfiles("prod");
    context.register(ProfileConfig.class);
    context.refresh();

    DemoBean demoBean = context.getBean(DemoBean.class);

    System.out.println(demoBean.getContent());

    context.close();
  }
}
