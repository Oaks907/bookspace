package DesignPattern.BehaviorMode.StatePattern;

/**
 * Create by haifei on 21/12/2018 4:56 PM.
 */
public class ConcreteStateB extends AbstractState {
  @Override
  public void doSomethingA() {
    System.out.println("ConcreteStateB doSomethingA");
  }

  @Override
  public void doSomethingB() {
    System.out.println("ConcreteStateB doSomethingB");
  }
}
