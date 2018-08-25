package com.wisely.highlight_spring4.ch3.taskexecutor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.AsyncConfigurer;

/**
 * Create by haifei on 25/8/2018.
 */
public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AsyncTaskConfig.class);

    AsyncTaskService taskService = context.getBean(AsyncTaskService.class);

    for (int i = 0; i < 10; i++) {
      taskService.executeAsyncTask(i);
      taskService.executeAsyncTaskPlus(i);
    }

    context.close();
  }
}
