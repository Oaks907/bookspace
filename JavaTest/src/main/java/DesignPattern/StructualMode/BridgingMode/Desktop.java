package DesignPattern.StructualMode.BridgingMode;

/**
 * Create by haifei on 21/12/2018 6:11 PM.
 */
public class Desktop extends Computer {

  public Desktop(Brand brand) {
    super(brand);
  }

  @Override
  public void info() {
    super.info();
    System.out.println("台式电脑");
  }
}
