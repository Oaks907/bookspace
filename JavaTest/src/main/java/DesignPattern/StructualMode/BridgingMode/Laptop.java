package DesignPattern.StructualMode.BridgingMode;

/**
 * Create by haifei on 21/12/2018 6:12 PM.
 */
public class Laptop extends Computer {

  public Laptop(Brand brand) {
    super(brand);
  }

  @Override
  public void info() {
    super.info();
    System.out.println("笔记本电脑");
  }
}
