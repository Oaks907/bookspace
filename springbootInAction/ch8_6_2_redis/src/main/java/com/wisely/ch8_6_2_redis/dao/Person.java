package com.wisely.ch8_6_2_redis.dao;

import java.io.Serializable;

/**
 * Create by haifei on 4/9/2018 7:37 AM.
 */
public class Person implements Serializable{

  private static final long serialVersionUID = 1L;

  private String id;
  private String name;
  private Integer age;

  public Person() {
    super();
  }
  public Person(String id,String name, Integer age) {
    super();
    this.id = id;
    this.name = name;
    this.age = age;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Integer getAge() {
    return age;
  }
  public void setAge(Integer age) {
    this.age = age;
  }
}
