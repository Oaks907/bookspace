package com.wisely.ch8_4_transaction.web;


import com.wisely.ch8_4_transaction.domain.Person;
import com.wisely.ch8_4_transaction.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by haifei on 2/9/2018 11:10 PM.
 */
@RestController
public class MyController {

  @Autowired
  DemoService demoService;

  @RequestMapping("/rollback")
  public Person rollback(Person person) { //1

    return demoService.savePersonWithRollBack(person);
  }

  @RequestMapping("/norollback")
  public Person noRollback(Person person) {//2

    return demoService.savePersonWithoutRollBack(person);


  }
}
