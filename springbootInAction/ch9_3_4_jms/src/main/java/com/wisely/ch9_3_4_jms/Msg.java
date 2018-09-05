package com.wisely.ch9_3_4_jms;

import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Create by haifei on 5/9/2018 10:51 PM.
 */
public class Msg implements MessageCreator {
  @Override
  public Message createMessage(Session session) throws JMSException {
    return session.createTextMessage("测试消息");
  }
}
