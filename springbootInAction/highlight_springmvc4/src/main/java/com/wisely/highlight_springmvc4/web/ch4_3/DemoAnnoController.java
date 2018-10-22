package com.wisely.highlight_springmvc4.web.ch4_3;

import com.wisely.highlight_springmvc4.Domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Create by haifei on 26/8/2018.
 */
@Controller
@RequestMapping("/anno")
public class DemoAnnoController {

  @ResponseBody
  @RequestMapping(produces = "text/plain")
  public String index(HttpServletRequest request) {
    return "url:" + request.getRequestURL() + " can access";
  }

  @RequestMapping(value = "/pathvar/{str}", produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String demoPathVar(@PathVariable String str, HttpServletRequest request) {
    return "url:" + request.getRequestURI() + " can access, str: " + str;
  }

  @RequestMapping(value = "/requestParam", produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String passRequestParam(Long id, HttpServletRequest request) {
    return "url:" + request.getRequestURI() + " can access, id:" + id;
  }


  //要求DemoObj构造题必须使用super方法
  @RequestMapping(value = "/obj", produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String passObj(DemoObj obj, HttpServletRequest request) {
    return "url:" + request.getRequestURI() + " can access, obj id:" + obj.getId() + ",name:" + obj.getName();
  }


  @RequestMapping(value = {"/name1", "/name2"}, produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String remove(HttpServletRequest request) {
    return "url:" + request.getRequestURI() + " can access";
  }


}
