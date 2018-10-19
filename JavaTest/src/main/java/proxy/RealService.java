package proxy;

/**
 * Create by haifei on 19/10/2018 4:07 PM.
 */
public class RealService implements Iservice {

  @Override
  public void sayHello(String str) {
    System.out.println("Hello, " + str + "!");
  }

  public void doSomething() {
    System.out.println("doing service....");
  }

}
