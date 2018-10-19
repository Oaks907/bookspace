package proxy.staticproxy;

import proxy.RealService;

/**
 * Create by haifei on 19/10/2018 4:13 PM.
 */
public class StaticProxyMain {

  public static void main(String[] args) {
    RealService realService = new RealService();
    StaticProxy staticProxy = new StaticProxy(realService);

    staticProxy.sayHello("static proxy");
    staticProxy.doSomething();
  }
}
