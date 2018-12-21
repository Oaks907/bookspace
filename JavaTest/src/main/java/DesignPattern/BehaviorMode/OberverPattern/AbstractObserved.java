package DesignPattern.BehaviorMode.OberverPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 21/12/2018 2:45 PM.
 */
public abstract class AbstractObserved {

  private List<AbstractObserver> observerList = new ArrayList<>();

  public void subscribe(AbstractObserver observer) {
    observerList.add(observer);
  }

  public void unSubscribe(AbstractObserver observer) {
    observerList.remove(observer);
  }

  public void action() {
    for (AbstractObserver item : observerList) {
      item.doSomething();
    }
  }
}
