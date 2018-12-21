package DesignPattern.BehaviorMode.IteratorPattern;

/**
 * Create by haifei on 21/12/2018 4:50 PM.
 */
public class Client {

  public static void main(String[] args) {
    Aggregate aggregate = new ConcreteAggregate();
    aggregate.add("111");
    aggregate.add("222");
    aggregate.add("333");

    final Iterator iterator = aggregate.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }
}
