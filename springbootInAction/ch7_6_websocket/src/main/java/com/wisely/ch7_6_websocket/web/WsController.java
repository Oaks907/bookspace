package com.wisely.ch7_6_websocket.web;

import com.wisely.ch7_6_websocket.domain.WiselyMessage;
import com.wisely.ch7_6_websocket.domain.WiselyResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @ClassName WsController
 * @User haifei
 * @date 29/8/2018 8:45 AM
 * @Description
 */
@Controller
public class WsController {

  @MessageMapping("/welcome")
  @SendTo("/topic/getResponse")
  public WiselyResponse say(WiselyMessage message) throws InterruptedException {
    Thread.sleep(3000);
    return new WiselyResponse("Welcome, " + message.getName() + "!");
  }
}
