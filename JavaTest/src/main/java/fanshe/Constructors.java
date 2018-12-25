package fanshe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Create by haifei on 25/12/2018 1:30 PM.
 */
public class Constructors {

  public static void main(String[] args) throws ClassNotFoundException {

    //加载class文件
    Class studentClass = Class.forName("fanshe.Student");

    //获取所有公有构造方法
    System.out.println("--所有公有构造方法");
    final Constructor[] constructors = studentClass.getConstructors();
    for (Constructor constructor : constructors) {
      System.out.println(constructor);
    }

    System.out.println("--获取所有构造方法");
    final Constructor[] declaredConstructors = studentClass.getDeclaredConstructors();
    for (Constructor constructor : declaredConstructors) {
      System.out.println(constructor);
    }

    System.out.println("--获取共有的变量");
    final Field[] fields = studentClass.getFields();
    for (Field field : fields) {
      System.out.println(field);
    }

    System.out.println("--获取所有的变量");
    final Field[] declaredFields = studentClass.getDeclaredFields();
    for (Field field : declaredFields) {
      System.out.println(field);
    }

    System.out.println("--公有、无参的构造方法");
    try {
      final Constructor constructor = studentClass.getConstructor();
      constructor.setAccessible(true);
      System.out.println(constructor.newInstance());
    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }

    System.out.println("--获取私有构造方法，并调用");
    try {
      final Constructor declaredConstructor = studentClass.getDeclaredConstructor(char.class);
      declaredConstructor.setAccessible(true);
      System.out.println(declaredConstructor.newInstance('男'));
    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }
}
