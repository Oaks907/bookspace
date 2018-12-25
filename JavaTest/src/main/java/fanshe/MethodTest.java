package fanshe;

import java.lang.reflect.Method;

/**
 * Create by haifei on 25/12/2018 2:37 PM.
 */
public class MethodTest {

  public static void main(String[] args) throws Exception {

    final Class<?> studentClass = Class.forName("fanshe.Student");

    System.out.println("--公有方法");
    final Method[] methods = studentClass.getMethods();
    for (Method method : methods) {
      System.out.println(method);
    }

    System.out.println("--所有方法");
    final Method[] declaredMethods = studentClass.getDeclaredMethods();
    for (Method method : declaredMethods) {
      System.out.println(method);
    }

    System.out.println("--调用公有方法");
    final Method show1 = studentClass.getMethod("show1", String.class);
    final Object instance = studentClass.newInstance();
    show1.invoke(instance, "show1");

    final Method show2 = studentClass.getDeclaredMethod("show2");
    show2.invoke(instance);

    final Method show3 = studentClass.getDeclaredMethod("show3");
    show3.invoke(instance);

    final Method show4 = studentClass.getDeclaredMethod("show4", int.class);
    show4.setAccessible(true);
    show4.invoke(instance, 4);
  }
}
