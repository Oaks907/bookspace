package DesignPattern.BehaviorMode.StatePattern;

/**
 * Create by haifei on 21/12/2018 4:56 PM.
 */
public class ConcreteStateA extends AbstractState{

  @Override
  public void doSomethingA() {
    System.out.println("ConcreteStateA doSomethingA");
  }

  @Override
  public void doSomethingB() {
    System.out.println("ConcreteStateA doSomethingB");
  }
}
