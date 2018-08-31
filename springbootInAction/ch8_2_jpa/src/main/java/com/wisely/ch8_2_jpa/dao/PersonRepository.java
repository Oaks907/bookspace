package com.wisely.ch8_2_jpa.dao;

import com.wisely.ch8_2_jpa.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Create by haifei on 31/8/2018 8:44 AM.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

  List<Person> findByAddress(String address);

  Person findByNameAndAddress(String name, String address);

  @Query("select p from Person p where p.name=:name and p.address=:address")
  Person withNameAndAddressQuery(@Param("name") String name, @Param("address") String address);

  //实体类声明的注解中的查询，名字对应
  Person withNameAndAddressNamedQuery(String name, String address);
}
