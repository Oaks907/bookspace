package DesignPattern.BehaviorMode.IteratorPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 21/12/2018 4:48 PM.
 */
public class ConcreteAggregate implements Aggregate {

  private List<Object> list = new ArrayList<>();

  @Override
  public void add(Object obj) {
    list.add(obj);
  }

  @Override
  public void remove(Object obj) {
    list.remove(obj);
  }

  @Override
  public Iterator iterator() {
    return new ConcreteIterator(list);
  }
}
