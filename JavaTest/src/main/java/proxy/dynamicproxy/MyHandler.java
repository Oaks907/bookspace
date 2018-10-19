package proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Create by haifei on 19/10/2018 4:19 PM.
 */
public class MyHandler implements InvocationHandler {

  private Object target;

  public Object getDynamicProxyInstance(Object object) {
    this.target = object;
    return Proxy.newProxyInstance(object.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("Dynamic proxy: before invoke");
    final Object invoke = method.invoke(target, args);
    System.out.println("Dynamic proxy: after invoke");
    return null;
  }


}
