package DesignPattern.BehaviorMode.ResponsibilityChain;

/**
 * Create by haifei on 21/12/2018 4:02 PM.
 */
public class ConcreteHandlerC extends AbstractHandler {

  @Override
  public boolean doSomething(String target) {

    if ("ConcreteHandlerC".equals(target)){
      System.out.println("ConcreteHandlerC do it.");
      return true;
    }

    System.out.println("ConcreteHandlerC not handle it");

    return false;
  }
}
