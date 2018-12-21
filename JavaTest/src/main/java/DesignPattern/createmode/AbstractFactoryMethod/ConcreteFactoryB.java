package DesignPattern.createmode.AbstractFactoryMethod;

/**
 * Create by haifei on 21/12/2018 2:26 PM.
 */
public class ConcreteFactoryB extends AbstractFactory {

  @Override
  public AbstractProductA createProductA() {
    return new ConcreteProductA2();
  }

  @Override
  public AbstractProductB createProductB() {
    return new ConcreteProductB2();
  }
}
