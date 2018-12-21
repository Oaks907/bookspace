package DesignPattern.BehaviorMode.StatePattern;

/**
 * Create by haifei on 21/12/2018 4:59 PM.
 */
public class Client {

  public static void main(String[] args) {
    final Operator operator = new Operator();
    operator.doSomethingA();
    operator.doSomethingB();
    System.out.println("--change--");
    operator.change();
    operator.doSomethingA();
    operator.doSomethingB();
  }
}
