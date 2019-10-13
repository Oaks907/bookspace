package S1201_S1300;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 13/10/2019 10:48 PM.
 */
public class S1221_SplitaStringinBalancedStrings {

  public int balancedStringSplit(String s) {
    String[] split = s.split("");
    int countR = 0;
    int countL = 0;
    int res = 0;

    for (int i = 0; i < split.length; i++) {
      if ("R".equals(split[i])) {
        countR++;
      } else if ("L".equals(split[i])) {
        countL++;
      }
      if (countR != 0 && countR == countL) {
        res++;
        countR = 0;
        countL = 0;
      }
    }
    return res;
  }

  @Test
  public void test() {

    int result = balancedStringSplit("RLRRLLRLRL");
    Assert.assertEquals(4, result);
  }

  @Test
  public void test1() {

    int result = balancedStringSplit("RLLLLRRRLR");
    Assert.assertEquals(3, result);
  }

  @Test
  public void test2() {

    int result = balancedStringSplit("LLLLRRRR");
    Assert.assertEquals(1, result);
  }


}
