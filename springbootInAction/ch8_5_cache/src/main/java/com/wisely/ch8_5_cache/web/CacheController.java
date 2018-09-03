package com.wisely.ch8_5_cache.web;

import com.wisely.ch8_5_cache.domain.Person;
import com.wisely.ch8_5_cache.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by haifei on 3/9/2018 8:25 AM.
 */
@RestController
public class CacheController {
  @Autowired
  DemoService demoService;

  @RequestMapping("/put")
  public Person put(Person person){
    return demoService.save(person);
  }

  @RequestMapping("/able")
  public Person cacheable(Person person){
    return demoService.findOne(person);
  }

  @RequestMapping("/evit")
  public String  evit(Long id){
    demoService.remove(id);
    return "ok";
  }
}
