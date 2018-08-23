package com.wisely.highlight_spring4.ch2.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * Create by haifei on 23/8/2018.
 */
public class DemoEvent extends ApplicationEvent {

  private static final long serialVersionUID = 1L;
  private String msg;


  public DemoEvent(Object source,String message) {
    super(source);
    this.msg = message;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
