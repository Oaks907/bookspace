package com.wisely.highlight_spring4.ch1.aop;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import sun.reflect.generics.tree.VoidDescriptor;

import java.lang.reflect.Method;

/**
 * Create by haifei on 21/8/2018.
 */
@Aspect
@Component
public class LogAspect {

  //声明切点
  @Pointcut("@annotation(com.wisely.highlight_spring4.ch1.aop.Action)")
  public void annotationPointCut() {

  }

  @Before("execution(* com.wisely.highlight_spring4.ch1.aop.DemoMethodService.*(..))")
  public void before(JoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    System.out.println("方法规则式拦截," + method.getName());
  }

  @After("annotationPointCut()")
  public void after(JoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    Action action = method.getAnnotation(Action.class);
    System.out.println("注解式拦截：" + action.name());

    System.out.println("name:" + method.getName());
    System.out.println("DeclaredAnnotations:" + method.getDeclaredAnnotations());
    System.out.println("Parameters:" + method.getParameters());
  }
}
