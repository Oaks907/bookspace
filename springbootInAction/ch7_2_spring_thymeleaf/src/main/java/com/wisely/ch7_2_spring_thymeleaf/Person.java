package com.wisely.ch7_2_spring_thymeleaf;

/**
 * @ClassName Person
 * @User haifei
 * @date 28/8/2018 10:46 PM
 * @Description
 */
public class Person {

  private String name;
  private Integer age;

  public Person() {
    super();
  }
  public Person(String name, Integer age) {
    super();
    this.name = name;
    this.age = age;
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
