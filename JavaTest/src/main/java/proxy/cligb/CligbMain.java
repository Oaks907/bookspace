package proxy.cligb;

import proxy.Iservice;
import proxy.RealService;

/**
 * Create by haifei on 19/10/2018 5:12 PM.
 */
public class CligbMain {
  public static void main(String[] args) {
    Iservice iservice = new RealService();
    CligbProxy cligbProxy = new CligbProxy();

    final Iservice proxyInstance = (Iservice) cligbProxy.getProxyInstance(iservice);

    proxyInstance.sayHello(" Cligb");
  }
}
