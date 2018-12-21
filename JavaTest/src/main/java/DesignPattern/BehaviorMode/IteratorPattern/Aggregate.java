package DesignPattern.BehaviorMode.IteratorPattern;

/**
 * Create by haifei on 21/12/2018 4:48 PM.
 */
public interface Aggregate {

  void add(Object obj);

  void remove(Object obj);

  Iterator iterator();
}
