package DesignPattern.StructualMode.BridgingMode;

/**
 * Create by haifei on 21/12/2018 6:13 PM.
 */
public class Client {
  public static void main(String[] args) {

    Computer computer = new Desktop(new LenovoBrand());

    computer.info();
  }
}
