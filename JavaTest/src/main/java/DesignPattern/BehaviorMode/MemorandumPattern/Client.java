package DesignPattern.BehaviorMode.MemorandumPattern;

/**
 * Create by haifei on 21/12/2018 3:50 PM.
 */
public class Client {

  public static void main(String[] args) {
    Originator originator = new Originator();
    originator.setState("初始状态");
    originator.display();

    CareTaker careTaker = new CareTaker();
    careTaker.saveMemento(originator.createMemento());

    originator.setState("修改一下");
    originator.display();

    System.out.println("--恢复--");

    originator.restoreMemento(careTaker.restoreMemento());
    originator.display();
  }
}
