package com.wisely.highlight_springmvc4.web.ch4_6;

import com.wisely.highlight_springmvc4.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MyRestController
 * @USER haifei
 * @DATE 27/8/2018 1:24 PM
 * @Description
 */
@RestController
public class MyRestController {

  @Autowired
  private DemoService demoService;

  @RequestMapping(value = "/testRest", produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String testRest() {
    return demoService.saySomething();
  }
}
