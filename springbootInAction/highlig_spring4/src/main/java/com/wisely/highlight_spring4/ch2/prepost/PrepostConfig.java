package com.wisely.highlight_spring4.ch2.prepost;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Create by haifei on 23/8/2018.
 */
@Configuration
@ComponentScan("com.wisely.highlight_spring4.ch2.prepost")
public class PrepostConfig {

  @Bean(initMethod = "init", destroyMethod = "destroy")
  public BeanWayService beanWayService() {
    return new BeanWayService();
  }

  @Bean
  public JSR250WayService jsr250WayService() {
    return new JSR250WayService();
  }
}
