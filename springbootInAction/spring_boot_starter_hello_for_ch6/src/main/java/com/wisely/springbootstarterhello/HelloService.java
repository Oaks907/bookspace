package com.wisely.springbootstarterhello;

/**
 * @ClassName HelloService
 * @User haifei
 * @date 28/8/2018 1:23 PM
 * @Description
 */
public class HelloService {

  private String msg;

  public String sayHello() {
    return "Hello," + msg;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
