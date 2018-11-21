package ioc;

import org.junit.Test;

/**
 * Create by haifei on 21/11/2018 11:32 PM.
 */
public class SimpleIOCTest {
  @Test
  public void getBean() throws Exception {
    String location = SimpleIOC.class.getClassLoader().getResource("ioc.xml").getFile();
    SimpleIOC bf = new SimpleIOC(location);
    Wheel wheel = (Wheel) bf.getBean("wheel");
    System.out.println(wheel);
    Car car = (Car) bf.getBean("car");
    System.out.println(car);
  }
}
