package com.wisely.highlight_spring4.ch1.javaconfig;

/**
 * Create by haifei on 21/8/2018.
 */
public class UserFunctionService {

  FunctionService functionService;

  public void setFunctionService(FunctionService functionService) {
    this.functionService = functionService;
  }

  public String sayHello(String world) {
    return functionService.sayHello(world);
  }
}
