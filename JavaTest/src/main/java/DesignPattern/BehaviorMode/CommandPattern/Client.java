package DesignPattern.BehaviorMode.CommandPattern;

/**
 * Create by haifei on 21/12/2018 3:04 PM.
 */
public class Client {

  public static void main(String[] args) {

    AbstractCommand command = new ConcreteCommand(new Receiver());

    Invoker invoker = new Invoker();
    invoker.setCommand(command);

    invoker.action();
  }
}
