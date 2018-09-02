package com.wisely.ch8_2_jpa.support;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

import static com.wisely.ch8_2_jpa.specs.CustomerSpecs.byAuto;

/**
 * Create by haifei on 2/9/2018 9:45 PM.
 */
public class CustomRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements
  CustomRespository<T, ID> {

  private final EntityManager entityManager;

  public CustomRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
    super(domainClass, entityManager);
    this.entityManager = entityManager;
  }

  @Override
  public Page<T> findByAuto(T example, Pageable pageable) {
    return findAll(byAuto(entityManager, example),pageable);
  }
}
