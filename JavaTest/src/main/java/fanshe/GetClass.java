package fanshe;

/**
 * Create by haifei on 25/12/2018 1:24 PM.
 */
public class GetClass {

  public static void main(String[] args) {

    //第一种：通过实例类来获取对应的 class 对象
    Student student = new Student();
    final Class<? extends Student> aClass = student.getClass();
    System.out.println(aClass.getName());

    //第二种：通过类的静态 class 属性
    Class bClass = Student.class;
    System.out.println(bClass);

    //第三种：通过类的路径进行加载
    final Class<?> cClass;
    try {
      cClass = Class.forName("fanshe.Student");
      System.out.println(cClass);
    } catch (ClassNotFoundException e) {
    }
  }
}
