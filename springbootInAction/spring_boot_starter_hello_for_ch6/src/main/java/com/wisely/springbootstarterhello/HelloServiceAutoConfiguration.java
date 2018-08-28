package com.wisely.springbootstarterhello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName HelloServiceAutoConfiguration
 * @User haifei
 * @date 28/8/2018 1:24 PM
 * @Description
 */
@Configuration
@EnableConfigurationProperties(HelloServiceProperties.class)
@ConditionalOnClass(HelloService.class)
@ConditionalOnProperty(prefix = "hello", value = "enabled", matchIfMissing = true)
public class HelloServiceAutoConfiguration {

  @Autowired
  private HelloServiceProperties helloProperties;

  @Bean
  public HelloService helloService() {
    HelloService helloService = new HelloService();
    helloService.setMsg(helloProperties.getMsg());
    return helloService;
  }
}
