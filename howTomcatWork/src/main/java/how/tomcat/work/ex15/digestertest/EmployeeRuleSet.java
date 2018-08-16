package how.tomcat.work.ex15.digestertest;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

/**
 * Create by haifei on 16/8/2018.
 */
public class EmployeeRuleSet extends RuleSetBase {
  public void addRuleInstances(Digester digester) {
    // add rules
    digester.addObjectCreate("employee", "how.tomcat.work.ex15.digestertest.Employee");
    digester.addSetProperties("employee");
    digester.addObjectCreate("employee/office", "how.tomcat.work.ex15.digestertest.Office");
    digester.addSetProperties("employee/office");
    digester.addSetNext("employee/office", "addOffice");
    digester.addObjectCreate("employee/office/address",
      "how.tomcat.work.ex15.digestertest.Address");
    digester.addSetProperties("employee/office/address");
    digester.addSetNext("employee/office/address", "setAddress");
  }
}
