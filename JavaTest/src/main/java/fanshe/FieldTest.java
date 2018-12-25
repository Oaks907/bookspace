package fanshe;

import java.lang.reflect.Field;

/**
 * Create by haifei on 25/12/2018 2:27 PM.
 */
public class FieldTest {

  public static void main(String[] args) throws Exception {
    Class studentClass = Class.forName("fanshe.Student");

    System.out.println("--获取公有字段并调用");
    final Field name = studentClass.getField("name");
    System.out.println(name);

    final Object instance = studentClass.newInstance();
    name.set(instance, "刘德华");

    Student student = (Student) instance;
    System.out.println(student.name);


    System.out.println("--获取私有字段并调用");
    final Field field = studentClass.getDeclaredField("phoneNum");
    System.out.println(field);
    field.setAccessible(true);//暴力反射，解除私有限定
    System.out.println(field);

    field.set(instance, "1111111");
    System.out.println(instance);
  }

}
