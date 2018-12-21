package DesignPattern.BehaviorMode.ResponsibilityChain;

/**
 * Create by haifei on 21/12/2018 4:02 PM.
 */
public class ConcreteHandlerA extends AbstractHandler {

  @Override
  public boolean doSomething(String target) {
    if ("ConcreteHandlerA".equals(target)) {
      System.out.println("ConcreteHandlerA do it.");
      return true;
    }

    System.out.println("ConcreteHandlerA not handle it");

    return false;
  }
}
