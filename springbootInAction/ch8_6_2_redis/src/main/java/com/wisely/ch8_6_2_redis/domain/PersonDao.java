package com.wisely.ch8_6_2_redis.domain;

import com.wisely.ch8_6_2_redis.dao.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Create by haifei on 4/9/2018 7:38 AM.
 */
@Repository
public class PersonDao {

  @Autowired
  StringRedisTemplate stringRedisTemplate;

  @Autowired
  RedisTemplate<Object, Object> redisTemplate;

  @Resource(name = "stringRedisTemplate")
  ValueOperations<String, String> valueOperationString;

  @Resource(name = "redisTemplate")
  ValueOperations<Object, Object> valueOperations;

  public void stringRedisTemplateDemo() {
    valueOperationString.set("xx", "yy");
  }

  public void save(Person person) {
    valueOperations.set(person.getId(), person);
  }

  public String getString() {
    return valueOperationString.get("xx");
  }

  public Person getPerson() {
    return (Person) valueOperations.get("1");
  }

}
