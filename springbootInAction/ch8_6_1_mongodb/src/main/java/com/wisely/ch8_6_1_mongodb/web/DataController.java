package com.wisely.ch8_6_1_mongodb.web;

import com.wisely.ch8_6_1_mongodb.dao.PersonRepository;
import com.wisely.ch8_6_1_mongodb.domain.Location;
import com.wisely.ch8_6_1_mongodb.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Create by haifei on 4/9/2018 7:12 AM.
 */
@RestController
public class DataController {

  @Autowired
  PersonRepository personRepository;

  @RequestMapping("/save")
  public Person save(){
    Person p = new Person("wyf",32);
    Collection<Location> locations =  new LinkedHashSet<Location>();
    Location loc1 = new Location("上海","2009");
    Location loc2 = new Location("合肥","2010");
    Location loc3 = new Location("广州","2011");
    Location loc4 = new Location("马鞍山","2012");
    locations.add(loc1);
    locations.add(loc2);
    locations.add(loc3);
    locations.add(loc4);
    p.setLocations(locations);

    return personRepository.save(p);
  }


  @RequestMapping("/q1")
  public Person q1(String name){
    return personRepository.findByName(name);
  }

  @RequestMapping("/q2")
  public List<Person> q2(Integer age){
    return personRepository.withQueryFindByAge(age);
  }
}
