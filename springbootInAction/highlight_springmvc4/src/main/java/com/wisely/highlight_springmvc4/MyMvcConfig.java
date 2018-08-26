package com.wisely.highlight_springmvc4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Create by haifei on 25/8/2018.
 */
@Configuration
@ComponentScan("com.wisely.highlight_springmvc4")
@EnableWebMvc
public class MyMvcConfig extends WebMvcConfigurerAdapter {

  @Bean
  public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("/WEB-INF/classes/views/");
    viewResolver.setSuffix(".jsp");
    viewResolver.setViewClass(JstlView.class);
    return viewResolver;
  }
}
