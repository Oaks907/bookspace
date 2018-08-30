package com.wisely.ch7_4_tomcatcofigure;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Create by haifei on 30/8/2018 ${time}.
 */
//@Component
//public class CustomServletContainer implements EmbeddedServletContainerCustomizer {
//
//  @Override
//  public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
//    configurableEmbeddedServletContainer.setPort(8888);
//    configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
//    configurableEmbeddedServletContainer.setSessionTimeout(10, TimeUnit.MINUTES);
//  }
//}
