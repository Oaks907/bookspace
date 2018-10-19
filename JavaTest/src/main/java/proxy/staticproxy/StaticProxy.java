package proxy.staticproxy;

import proxy.Iservice;
import proxy.RealService;

/**
 * Create by haifei on 19/10/2018 4:09 PM.
 * 静态代理
 */
public class StaticProxy implements Iservice{

  private RealService realService;

  public StaticProxy(RealService realService) {
    this.realService = realService;
  }


  @Override
  public void sayHello(String str) {
    System.out.println("proxy: before say hello.");
    realService.sayHello(str);
    System.out.println("proxy: after say hello.");
  }

  public void doSomething() {
    System.out.println("proxy:before do something.");
    realService.doSomething();
    System.out.println("proxy:after do something.");
  }
}
