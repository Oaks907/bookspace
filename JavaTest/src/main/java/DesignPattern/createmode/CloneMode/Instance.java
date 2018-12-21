package DesignPattern.createmode.CloneMode;

import lombok.Data;

/**
 * Create by haifei on 21/12/2018 11:49 AM.
 */
@Data
public class Instance {
  private String a;
  private String b;

  @Override
  protected Object clone() throws CloneNotSupportedException {

    Instance instance = new Instance();
    instance.setA(a);
    instance.setB(b);

    return instance;
  }
}
