package com.wisely.highlight_spring4.ch2.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Create by haifei on 23/8/2018.
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {


  @Override
  public void onApplicationEvent(DemoEvent demoEvent) {
    String msg = demoEvent.getMsg();

    System.out.println("DemoListener接收到了Event messge:" + msg);
  }
}
