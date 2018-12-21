package DesignPattern.createmode.BuilderMode;

/**
 * Create by haifei on 21/12/2018 11:44 AM.
 */
public class Builder {

  private RealObject realObject;

  public Builder buildFieldA(String a) {
    realObject.setFieldA(a);
    return this;
  }

  public Builder builderFieldB(String b) {
    realObject.setFieldB(b);
    return this;
  }

  public RealObject builder() {
    return realObject;
  }
}
