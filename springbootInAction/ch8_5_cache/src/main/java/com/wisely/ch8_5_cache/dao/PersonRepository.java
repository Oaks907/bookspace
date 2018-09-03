package com.wisely.ch8_5_cache.dao;


import com.wisely.ch8_5_cache.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by haifei on 2/9/2018 10:25 PM.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

}
