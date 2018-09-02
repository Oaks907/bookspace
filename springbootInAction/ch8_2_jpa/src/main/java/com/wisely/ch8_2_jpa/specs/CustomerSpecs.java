package com.wisely.ch8_2_jpa.specs;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Iterables.toArray;

/**
 * Create by haifei on 2/9/2018 9:26 PM.
 */
public class CustomerSpecs {

  public static <T> Specification<T> byAuto(final EntityManager entityManager, final T example) {

    final Class<T> type = (Class<T>) example.getClass();

    return new Specification<T>() {
      @Nullable
      @Override
      public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicateList = new ArrayList<>();

        EntityType<T> entityType = entityManager.getMetamodel().entity(type);

        for (Attribute<T, ?> attr : entityType.getDeclaredAttributes()) {
          Object attrValue = getValue(example, attr);

          if (attrValue != null) {
            if (attr.getJavaType() == String.class) {
              if (!StringUtils.isEmpty(attrValue)) {
                predicateList.add(criteriaBuilder.like(root.get(attribute(entityType, attr.getName(), String.class)),
                  pattern((String) attrValue)));
              }
            } else {
              predicateList.add(criteriaBuilder.equal(root.get(attribute(entityType, attr.getName(), attrValue.getClass())),
                attrValue));
            }
          }
        }

        return predicateList.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.and(toArray(predicateList,
          Predicate.class));
      }

      //通过反射获取实体类对象对应属性的属性值
      private <T> Object getValue(T example, Attribute<T, ?> attr) {
        return ReflectionUtils.getField((Field) attr.getJavaMember(), example);
      }

      //获取当前实体类的当前属性的SingularAttri，这个包含的是实体类的某个单独的属性
      private <E, T>SingularAttribute<T, E> attribute(EntityType<T> entity, String fieldName, Class<E> fieldClass) {
        return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
      }


    };
  }

  static private String pattern(String str) {
    return "%" + str + "%";
  }
}
