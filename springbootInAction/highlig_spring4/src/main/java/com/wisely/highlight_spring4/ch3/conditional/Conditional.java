package com.wisely.highlight_spring4.ch3.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create by haifei on 25/8/2018.
 */
@Configuration
public class Conditional {

  @Bean
  @org.springframework.context.annotation.Conditional(WindowsCondition.class)
  public ListService windowListService() {
    return new WindowListService();
  }

  @Bean
  @org.springframework.context.annotation.Conditional(MacCondition.class)
  public ListService macListService() {
    return new MacListService();
  }
}
