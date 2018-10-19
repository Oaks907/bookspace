package proxy.dynamicproxy;

import proxy.Iservice;
import proxy.RealService;

/**
 * Create by haifei on 19/10/2018 4:18 PM.
 */
public class DynamicProxyMain {

  public static void main(String[] args) {
    Iservice realService = new RealService();
    MyHandler myHandler = new MyHandler();

    Iservice iservice = (Iservice) myHandler.getDynamicProxyInstance(realService);

    consumer(iservice);
  }

  public static void consumer(Iservice iface) {
    iface.sayHello("dynamic proxy");
  }
}
