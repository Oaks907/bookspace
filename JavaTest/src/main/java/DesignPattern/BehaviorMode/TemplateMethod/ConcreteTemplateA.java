package DesignPattern.BehaviorMode.TemplateMethod;

/**
 * Create by haifei on 21/12/2018 3:11 PM.
 */
public class ConcreteTemplateA extends AbstractTemplate {

  @Override
  void step1() {
    System.out.println("ConcreteTemplateA step1");
  }

  @Override
  void step2() {
    System.out.println("ConcreteTemplateA step2");
  }

  @Override
  void step3() {
    System.out.println("ConcreteTemplateA step3");
  }
}
