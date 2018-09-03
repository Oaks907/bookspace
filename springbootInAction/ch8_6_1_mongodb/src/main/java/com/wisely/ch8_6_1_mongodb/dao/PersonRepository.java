package com.wisely.ch8_6_1_mongodb.dao;

import com.wisely.ch8_6_1_mongodb.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Create by haifei on 4/9/2018 7:09 AM.
 */
public interface PersonRepository extends MongoRepository<Person, String> {

  Person findByName(String name);

  @Query("{'age':?0}")
  List<Person> withQueryFindByAge(Integer age);
}
