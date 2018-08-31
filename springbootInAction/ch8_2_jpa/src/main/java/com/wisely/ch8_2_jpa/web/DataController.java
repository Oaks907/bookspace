package com.wisely.ch8_2_jpa.web;

import com.wisely.ch8_2_jpa.dao.PersonRepository;
import com.wisely.ch8_2_jpa.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.RequestWrapper;
import java.util.List;

/**
 * Create by haifei on 31/8/2018 8:56 AM.
 */
@RestController
public class DataController {

  @Autowired
  PersonRepository personRepository;


  /**
   * 保存
   * save支持批量保存：<S extends T> Iterable<S> save(Iterable<S> entities);
   * <p>
   * 删除：
   * 删除支持使用id，对象以，批量删除及删除全部：
   * void delete(ID id);
   * void delete(T entity);
   * void delete(Iterable<? extends T> entities);
   * void deleteAll();
   */
  @RequestMapping("/save")
  public Person save(String name, String address, Integer age) {

    Person p = personRepository.save(new Person(null, name, age, address));

    return p;
  }


  @RequestMapping("/q1")
  public List<Person> q1(String address) {

    return personRepository.findByAddress(address);
  }

  @RequestMapping("/q2")
  public Person q2(String name, String address) {
    return personRepository.findByNameAndAddress(name, address);
  }

  @RequestMapping("/q3")
  public Person q3(String name, String address) {
    return personRepository.withNameAndAddressQuery(name, address);
  }

  @RequestMapping("/q4")
  public Person q4(String name, String address) {
    return personRepository.withNameAndAddressNamedQuery(name, address);
  }

  @RequestMapping("/sort")
  public List<Person> sort() {
    return personRepository.findAll(new Sort(Direction.ASC, "age"));
  }

  @RequestMapping("/page")
  public Page<Person> page() {
    return personRepository.findAll(new PageRequest(1, 2));
  }

  @RequestMapping("/all")
  public List<Person> all() {
    return personRepository.findAll();
  }
}
