package com.wisly.ch9_3_5_amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Create by haifei on 5/9/2018 11:13 PM.
 */
@Component
public class Receiver {

  @RabbitListener(queues = "my-queue")
  public void receiveMessage(String message) {
    System.out.println("Received:<" + message + ">");
  }

}
