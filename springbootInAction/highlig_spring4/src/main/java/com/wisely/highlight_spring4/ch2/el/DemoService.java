package com.wisely.highlight_spring4.ch2.el;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Create by haifei on 22/8/2018.
 */
@Service
public class DemoService {

  @Value("其他类的属性")
  private String another;

  public String getAnother() {
    return another;
  }

  public void setAnother(String another) {
    this.another = another;
  }
}
