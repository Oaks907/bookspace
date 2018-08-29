package com.wisely.ch_6_5_spring_configuration;

import com.wisely.springbootstarterhello.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Ch65SpringConfigurationApplication {

  @Autowired
  private HelloService helloService;

  @RequestMapping("/index")
  public String index() {
    return helloService.sayHello();
  }

  public static void main(String[] args) {
    SpringApplication.run(Ch65SpringConfigurationApplication.class, args);
  }
}
