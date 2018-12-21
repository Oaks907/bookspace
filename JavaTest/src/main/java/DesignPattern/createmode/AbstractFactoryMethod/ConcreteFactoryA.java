package DesignPattern.createmode.AbstractFactoryMethod;

/**
 * Create by haifei on 21/12/2018 2:25 PM.
 */
public class ConcreteFactoryA extends AbstractFactory{
  @Override
  public AbstractProductA createProductA() {
    return new ConcreteProductA1();
  }

  @Override
  public AbstractProductB createProductB() {
    return new ConcreteProductB1();
  }
}
