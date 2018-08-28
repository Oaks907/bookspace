package com.wisely.springbootstarterhello;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName HelloProperties
 * @User haifei
 * @date 28/8/2018 1:20 PM
 * @Description
 */
//在application.properties中设置，如果不设置，默认的值为：hello.msg = world
@ConfigurationProperties(prefix = "hello")
public class HelloServiceProperties {
  private static final String MSG = "world";

  private String msg = MSG;

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
