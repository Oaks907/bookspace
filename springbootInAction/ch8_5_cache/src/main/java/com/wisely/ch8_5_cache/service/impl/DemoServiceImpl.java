package com.wisely.ch8_5_cache.service.impl;

import com.wisely.ch8_5_cache.dao.PersonRepository;
import com.wisely.ch8_5_cache.domain.Person;
import com.wisely.ch8_5_cache.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Create by haifei on 3/9/2018 8:13 AM.
 */
@Service
public class DemoServiceImpl implements DemoService {


  @Autowired
  private PersonRepository personRepository;

  @Override
  @CachePut(value = "people", key = "#person.id")
  public Person save(Person person) {
    Person p = personRepository.save(person);
    System.out.println("为Id，key为：" + p.getId() + "数据库缓存");
    return p;
  }

  @Override
  @CacheEvict(value = "people")
  public void remove(Long id) {
    System.out.println("删除了id、key为" + id + "的数据缓存");
  }

  @Override
  @Cacheable(value="people", key = "#person.id")
  public Person findOne(Person person) {
    Person p = personRepository.findById(person.getId()).get();
    System.out.println("为id、key为:"+p.getId()+"数据做了缓存");
    return p;
  }
}
