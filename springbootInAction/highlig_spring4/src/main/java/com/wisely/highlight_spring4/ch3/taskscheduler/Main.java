package com.wisely.highlight_spring4.ch3.taskscheduler;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Create by haifei on 25/8/2018.
 */
public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskScheduleConfig.class);
  }
}
