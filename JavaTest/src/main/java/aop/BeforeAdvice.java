package aop;

import java.lang.reflect.Method;

/**
 * Create by haifei on 24/12/2018 5:25 PM.
 */
public class BeforeAdvice implements Advice {

  private Object bean;
  private MethodInvocation methodInvocation;

  public BeforeAdvice(Object bean, MethodInvocation methodInvocation) {
    this.bean = bean;
    this.methodInvocation = methodInvocation;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    methodInvocation.invoke();
    return method.invoke(bean, args);
  }
}
