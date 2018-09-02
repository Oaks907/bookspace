package com.wisely.ch8_4_transaction.dao;


import com.wisely.ch8_4_transaction.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by haifei on 2/9/2018 10:25 PM.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

}
