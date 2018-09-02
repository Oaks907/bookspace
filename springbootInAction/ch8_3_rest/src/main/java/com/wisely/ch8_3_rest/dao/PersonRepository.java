package com.wisely.ch8_3_rest.dao;

import com.wisely.ch8_3_rest.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Create by haifei on 2/9/2018 10:25 PM.
 */
@RepositoryRestResource(path = "people")
public interface PersonRepository extends JpaRepository<Person, Long> {

  Person findByNameStartsWith(String name);
  
}
