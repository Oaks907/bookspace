package DesignPattern.BehaviorMode.ResponsibilityChain;

/**
 * Create by haifei on 21/12/2018 4:05 PM.
 */
public class Client {

  public static void main(String[] args) {
    AbstractHandler handlerA = new ConcreteHandlerA();
    AbstractHandler handlerB = new ConcreteHandlerB();
    AbstractHandler handlerC = new ConcreteHandlerC();

    handlerA.setNext(handlerB);
    handlerB.setNext(handlerC);

    handlerA.handle("ConcreteHandlerC");
  }
}
