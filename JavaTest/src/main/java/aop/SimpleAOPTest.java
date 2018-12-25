package aop;

/**
 * Create by haifei on 24/12/2018 5:34 PM.
 */
public class SimpleAOPTest {

  public static void main(String[] args) {
    MethodInvocation invocation = () -> {
      System.out.println("before task start");
    };

    HelloService helloService = new HelloServiceImpl();

    Advice advice = new BeforeAdvice(helloService, invocation);

    HelloService helloServiceProxy = (HelloService) SimpleAOP.getProxy(helloService, advice);

    helloServiceProxy.sayHello();
  }
}
