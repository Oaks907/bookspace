package aop;

/**
 * Create by haifei on 24/12/2018 5:33 PM.
 */
public class HelloServiceImpl implements HelloService {
  @Override
  public void sayHello() {
    System.out.println("hello world");
  }
}
