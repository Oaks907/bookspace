package DesignPattern.BehaviorMode.IteratorPattern;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 21/12/2018 4:13 PM.
 */
public class ConcreteIterator<T> implements Iterator {

  private List<T> list;

  int cursor = 0;

  public ConcreteIterator(List list) {
    this.list = list;
  }

  @Override
  public boolean hasNext() {
    if (cursor >= list.size()) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public T next() {
    return list.get(cursor++);
  }
}
