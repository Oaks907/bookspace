package com.wisely.highlight_spring4.ch1.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create by haifei on 21/8/2018.
 */
@Configuration
public class JavaConfig {

  @Bean
  public FunctionService functionService() {
    return new FunctionService();
  }

  @Bean
  public UserFunctionService userFunctionService() {
    UserFunctionService userFunctionService = new UserFunctionService();
    userFunctionService.setFunctionService(functionService());
    return  userFunctionService;
  }

//  @Bean
//  public UserFunctionService userFunctionService(FunctionService functionService) {
//    UserFunctionService userFunctionService = new UserFunctionService();
//    userFunctionService.setFunctionService(functionService);
//    return  userFunctionService;
//  }
}
