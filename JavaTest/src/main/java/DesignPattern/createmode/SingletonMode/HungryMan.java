package DesignPattern.createmode.SingletonMode;

/**
 * Create by haifei on 21/12/2018 11:27 AM.
 */
public class HungryMan {

  private static final HungryMan hungryMan = new HungryMan();

  private HungryMan() {

  }

  public static HungryMan getInstance() {
    return hungryMan;
  }
}
