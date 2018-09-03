package com.wisely.ch8_5_cache.service;

import com.wisely.ch8_5_cache.domain.Person;

/**
 * Create by haifei on 3/9/2018 8:12 AM.
 */
public interface DemoService {

  public Person save(Person person);

  public void remove(Long id);

  public Person findOne(Person person);
}
