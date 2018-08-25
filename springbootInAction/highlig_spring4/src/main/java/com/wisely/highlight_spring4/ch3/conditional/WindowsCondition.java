package com.wisely.highlight_spring4.ch3.conditional;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Create by haifei on 25/8/2018.
 */
public class WindowsCondition implements Condition {

  @Override
  public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
    return conditionContext.getEnvironment().getProperty("os.name").contains("Windows");
  }
}
