package S701_800;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 13/11/2019 11:35 PM.
 */
public class S771_JewelsandStones {

  public int numJewelsInStones(String J, String S) {
    if (J == null || S == null) {
      return 0;
    }

    int result = 0;
    String[] split = J.split("");
    for (int i = 0; i < split.length; i++) {
      int old = S.length();
      S = S.replace(split[i], "");
      result += old - S.length();
    }

    return result;
  }

  @Test
  public void test() {
    int result = numJewelsInStones("aA", "aAAbbbb");

    Assert.assertEquals(3, result);
  }
}
