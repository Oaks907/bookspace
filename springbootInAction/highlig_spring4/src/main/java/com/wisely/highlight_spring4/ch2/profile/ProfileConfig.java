package com.wisely.highlight_spring4.ch2.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Create by haifei on 23/8/2018.
 */
@Configuration
public class ProfileConfig {

  @Bean
  @Profile("dev")
  public DemoBean devDemoBean() {
    return new DemoBean("from dev bean");
  }

  @Bean
  @Profile("prod")
  public DemoBean prodDemoBean() {
    return new DemoBean("from prod bean");
  }
}
