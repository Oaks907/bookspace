package com.wisely.highlight_springmvc4.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Create by haifei on 26/8/2018.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

  //全局的异常处理
  @ExceptionHandler(value = Exception.class)
  public ModelAndView exception(Exception exception, WebRequest request) {
    ModelAndView modelAndView = new ModelAndView("error");
    modelAndView.addObject("errorMessage", exception.getMessage());
    return modelAndView;
  }

  //添加全局变量
  @ModelAttribute
  public void addAttributes(Model model) {
    model.addAttribute("msg", "额外信息");
  }

  //定制化数据绑定
  @InitBinder
  public void initBinder(WebDataBinder webDataBinder) {
    webDataBinder.setDisallowedFields("id");
  }
}
