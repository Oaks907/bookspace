package com.wisely.ch8_4_transaction.service.impl;


import com.wisely.ch8_4_transaction.dao.PersonRepository;
import com.wisely.ch8_4_transaction.domain.Person;
import com.wisely.ch8_4_transaction.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create by haifei on 2/9/2018 11:05 PM.
 */
@Service
public class DemoServiceImpl implements DemoService {

  @Autowired
  PersonRepository personRepository;


  @Transactional(rollbackFor = {IllegalArgumentException.class})
  @Override
  public Person savePersonWithRollBack(Person person) {
    Person p = personRepository.save(person);

    if (person.getName().equals("汪云飞")) {
      throw new IllegalArgumentException("汪云飞已存在，数据将回滚"); //3
    }
    return p;
  }

  @Transactional(noRollbackFor = {IllegalArgumentException.class})
  @Override
  public Person savePersonWithoutRollBack(Person person) {
    Person p = personRepository.save(person);

    if (person.getName().equals("汪云飞")) {
      throw new IllegalArgumentException("汪云飞虽已存在，数据将不会回滚");
    }
    return p;
  }
}
