package aop;

import java.lang.reflect.Proxy;

/**
 * Create by haifei on 24/12/2018 5:26 PM.
 */
public class SimpleAOP {

  public static Object getProxy(Object bean, Advice advice) {

    return Proxy.newProxyInstance(SimpleAOP.class.getClassLoader(),
      bean.getClass().getInterfaces(), advice);
  }
}
