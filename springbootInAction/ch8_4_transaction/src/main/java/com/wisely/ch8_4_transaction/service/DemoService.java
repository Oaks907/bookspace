package com.wisely.ch8_4_transaction.service;


import com.wisely.ch8_4_transaction.domain.Person;

/**
 * Create by haifei on 2/9/2018 11:04 PM.
 */
public interface DemoService {

  public Person savePersonWithRollBack(Person person);

  public Person savePersonWithoutRollBack(Person person);

}
