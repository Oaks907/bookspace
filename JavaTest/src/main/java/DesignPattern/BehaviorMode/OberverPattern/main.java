package DesignPattern.BehaviorMode.OberverPattern;

/**
 * Create by haifei on 21/12/2018 2:55 PM.
 */
public class main {

  public static void main(String[] args) {
    AbstractObserved observed = new ConcreteObserved();
    AbstractObserver observerA = new ConcreteObserverA();
    AbstractObserver observerB = new ConcreteObserverB();

    observed.subscribe(observerA);
    observed.subscribe(observerB);

    observed.action();
  }
}
