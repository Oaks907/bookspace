package com.wisely.highlight_springmvc4.web.ch4_5;

import com.wisely.highlight_springmvc4.Domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create by haifei on 26/8/2018.
 */
@Controller
public class ConverterController {

  @ResponseBody
  @RequestMapping(value = "/convert", produces = "application/x-wisely")
  public DemoObj convert(@RequestBody DemoObj demoObj) {
    return demoObj;
  }

}
