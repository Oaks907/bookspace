package DesignPattern.createmode.FactoryMethod;

/**
 * Create by haifei on 21/12/2018 12:10 PM.
 */
public class ConcreteFactoryA extends AbstractFactory{

  @Override
  public AbstractProduct createProduct() {
    return new ConcreteProductA();
  }
}