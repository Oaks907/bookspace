package com.wisely.highlight_springmvc4;

import com.wisely.highlight_springmvc4.Interceptor.DemoInterceptor;
import com.wisely.highlight_springmvc4.messageconverter.MyMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

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


  //静态资源配置
  //addResourceHandler指对外暴露的目录， addResourceLocations指文件放置的目录
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
  }

  //拦截器的Bean
  @Bean
  public DemoInterceptor demoInterceptor() {
    return new DemoInterceptor();
  }

  /*
   * 添加拦截器
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(demoInterceptor());
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/index").setViewName("/index");
    registry.addViewController("/toUpload").setViewName("/upload");
    registry.addViewController("/converter").setViewName("/converter");
  }

  @Bean
  public MultipartResolver multipartResolver() {
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    multipartResolver.setMaxInMemorySize(1000000);
    return multipartResolver;
  }


  //使用自定义的MessageConvert
  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(converter());
  }

  @Bean
  public MyMessageConverter converter() {
    return new MyMessageConverter();
  }
}
