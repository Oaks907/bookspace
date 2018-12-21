package DesignPattern.BehaviorMode.StatePattern;

/**
 * Create by haifei on 21/12/2018 4:57 PM.
 */
public class Operator {
  public AbstractState state = new ConcreteStateA();
  public boolean flag = true;

  public void change(){
    if (flag) {
      state = new ConcreteStateA();
    } else {
      state = new ConcreteStateB();
    }
    flag = !flag;
  }

  public void doSomethingA() {
    state.doSomethingA();
  }

  public void doSomethingB() {
    state.doSomethingB();
  }
}
