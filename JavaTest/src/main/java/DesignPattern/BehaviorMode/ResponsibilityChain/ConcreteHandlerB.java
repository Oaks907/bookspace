package DesignPattern.BehaviorMode.ResponsibilityChain;

/**
 * Create by haifei on 21/12/2018 4:02 PM.
 */
public class ConcreteHandlerB extends AbstractHandler {


  @Override
  public boolean doSomething(String target) {

    if ("ConcreteHandlerB".equals(target)){
      System.out.println("ConcreteHandlerB do it.");
      return true;
    }
    System.out.println("ConcreteHandlerB not handle it");

    return false;
  }
}
