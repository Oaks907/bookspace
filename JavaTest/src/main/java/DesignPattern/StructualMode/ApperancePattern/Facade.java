package DesignPattern.StructualMode.ApperancePattern;

/**
 * Create by haifei on 21/12/2018 5:46 PM.
 */
public class Facade {

  public void operator() {
    new SystemA().methodA();
    new SystemB().methodB();
    new SystemC().methodC();
  }
}
