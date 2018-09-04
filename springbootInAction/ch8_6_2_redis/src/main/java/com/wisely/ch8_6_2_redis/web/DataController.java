package com.wisely.ch8_6_2_redis.web;

import com.wisely.ch8_6_2_redis.dao.Person;
import com.wisely.ch8_6_2_redis.domain.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by haifei on 4/9/2018 7:53 AM.
 */
@RestController
public class DataController {

  @Autowired
  private PersonDao personDao;

  @RequestMapping("/set")
  public String set() {
    Person person = new Person("1", "wyf", 32);
    personDao.save(person);
    personDao.stringRedisTemplateDemo();
    return "ok";
  }

  @RequestMapping("/getStr")
  public String getStr() {
    return personDao.getString();
  }

  @RequestMapping("/getPerson")
  public Person getPerson() {
    return personDao.getPerson();
  }
}
