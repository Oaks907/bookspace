package how.tomcat.work.ex15.digestertest;

import org.apache.commons.digester.Digester;

import java.io.File;

/**
 * Create by haifei on 16/8/2018.
 */
public class Test01 {

  public static void main(String[] args) {
    String path = System.getProperty("user.dir") + File.separator + "etc";
    File file = new File(path, "employee1.xml");
    Digester digester = new Digester();
    digester.addObjectCreate("employee", "how.tomcat.work.ex15.digestertest.Employee");
    digester.addSetProperties("employee");
    digester.addCallMethod("employee", "printName");

    try {
      Employee employee = (Employee) digester.parse(file);
      System.out.println("FirstName:" + employee.getFirstName());
      System.out.println("LastName:" + employee.getLastName());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
