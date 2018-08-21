package com.wisely.highlight_spring4.ch1.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by haifei on 21/8/2018.
 */
@Service
public class UserFunctionService {

  @Autowired
  FunctionService functionService;

  public String sayHello(String world) {
    return functionService.sayHello(world);
  }
}
