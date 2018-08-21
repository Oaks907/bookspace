package com.wisely.highlight_spring4.ch1.di;

import org.springframework.stereotype.Service;

/**
 * Create by haifei on 21/8/2018.
 */
@Service
public class FunctionService {

  public String sayHello(String word) {
    return "Hello " + word + " !";
  }
}
