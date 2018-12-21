package DesignPattern.StructualMode.BridgingMode;

/**
 * Create by haifei on 21/12/2018 6:05 PM.
 */
public abstract class Computer {

  Brand brand;

  public Computer(Brand brand) {
    this.brand = brand;
  }

  public void info() {
    this.brand.info();
  }
}
