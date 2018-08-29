package com.wisely.ch7_6_websocket.domain;

/**
 * @ClassName WiselyResponse
 * @User haifei
 * @date 29/8/2018 8:43 AM
 * @Description
 */
public class WiselyResponse {

  private String responseMessage;

  public WiselyResponse(String responseMessage) {
    this.responseMessage = responseMessage;
  }

  public String getResponseMessage() {
    return responseMessage;
  }

  public void setResponseMessage(String responseMessage) {
    this.responseMessage = responseMessage;
  }
}
