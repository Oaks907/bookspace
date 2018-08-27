package com.wisely.highlight_springmvc4.web.ch4_6;

import com.wisely.highlight_springmvc4.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName NormalController
 * @USER haifei
 * @DATE 27/8/2018 1:26 PM
 * @Description
 */
@Controller
public class NormalController {

  @Autowired
  private DemoService demoService;

  @RequestMapping("/normal")
  public String testPage(Model model) {
    model.addAttribute("msg", demoService.saySomething());
    return "page";
  }
}
