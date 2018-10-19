package proxy.cligb;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Create by haifei on 19/10/2018 5:06 PM.
 */
public class CligbProxy implements MethodInterceptor {

  private Object target;

  public Object getProxyInstance(Object target) {
    this.target = target;
    Enhancer enhancer = new Enhancer();
    enhancer.setCallback(this);
    enhancer.setSuperclass(this.target.getClass());
    return enhancer.create();
  }


  @Override
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
    System.out.println("before target method...");
    Object result = methodProxy.invokeSuper(o, objects);
    System.out.println("after target method...");
    return result;
  }
}
