package com.wisely.ch9_3_4_jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Create by haifei on 5/9/2018 10:57 PM.
 */
@Component
public class Receiver {

  @JmsListener(destination = "my-destination")
  public void receiveMessage(String message){
    System.out.println("接收到了Message：《" + message + "》");
  }
}
