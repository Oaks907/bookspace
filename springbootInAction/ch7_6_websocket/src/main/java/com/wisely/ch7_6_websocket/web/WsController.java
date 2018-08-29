package com.wisely.ch7_6_websocket.web;

import com.wisely.ch7_6_websocket.domain.WiselyMessage;
import com.wisely.ch7_6_websocket.domain.WiselyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * @ClassName WsController
 * @User haifei
 * @date 29/8/2018 8:45 AM
 * @Description
 */
@Controller
public class WsController {

  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;

  @MessageMapping("/welcome")
  @SendTo("/topic/getResponse")
  public WiselyResponse say(WiselyMessage message) throws InterruptedException {
    Thread.sleep(3000);
    return new WiselyResponse("Welcome, " + message.getName() + "!");
  }

  @MessageMapping("/chat")
  public void handleChat(Principal principal, String msg) {
    if (principal.getName().equals("123")) {
      simpMessagingTemplate.convertAndSendToUser("456", "/queue/notifications",
        principal.getName() + "-send:" + msg);
    } else {
      simpMessagingTemplate.convertAndSendToUser("123", "/queue/notifications",
        principal.getName() + "-send:" + msg);
    }
  }
}
