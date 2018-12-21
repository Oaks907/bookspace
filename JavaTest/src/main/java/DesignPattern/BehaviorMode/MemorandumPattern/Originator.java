package DesignPattern.BehaviorMode.MemorandumPattern;

/**
 * Create by haifei on 21/12/2018 3:28 PM.
 */
public class Originator {

  private String state;

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Memento createMemento() {
    return new Memento(state);
  }

  public void restoreMemento(Memento memento) {
    this.setState(memento.getState());
  }

  public void display() {
    System.out.println("current state:" + state);
  }
}
