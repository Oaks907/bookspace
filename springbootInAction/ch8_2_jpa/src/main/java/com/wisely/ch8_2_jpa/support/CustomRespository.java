package com.wisely.ch8_2_jpa.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Create by haifei on 31/8/2018 8:46 AM.
 */
@NoRepositoryBean
public interface CustomRespository<T, ID extends Serializable> extends JpaRepository<T, ID> ,JpaSpecificationExecutor<T> {

  Page<T> findByAuto(T example, Pageable pageable);
}
