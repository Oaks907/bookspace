package DesignPattern.BehaviorMode.MemorandumPattern;

/**
 * Create by haifei on 21/12/2018 3:28 PM.
 *
 * careTaker的作用是为了保存 Memento对象
 */
public class CareTaker {

  private Memento memento;

  public void saveMemento(Memento memento) {
    this.memento = memento;
  }

  public Memento restoreMemento() {
    return memento;
  }
}
