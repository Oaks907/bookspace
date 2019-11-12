package S1101_S1200;

import org.junit.Test;

/**
 * Create by haifei on 12/11/2019 11:33 PM.
 */
public class S1108_DefanginganIPAddress {

  public String defangIPaddr(String address) {
    if (null == address) {
      return address;
    }
    return address.replace(".", "[.]");
  }

  @Test
  public void test() {

    String address = "1.1.1.1";

    System.out.println(defangIPaddr(address));
  }
}
