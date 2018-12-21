package DesignPattern.StructualMode.BridgingMode;

/**
 * Create by haifei on 21/12/2018 6:07 PM.
 */
public class LenovoBrand implements Brand {

  @Override
  public void info() {
    System.out.println("联想");
  }
}
