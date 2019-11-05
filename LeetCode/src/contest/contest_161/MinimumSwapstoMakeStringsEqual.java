package contest.contest_161;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 3/11/2019 10:32 AM.
 */
public class MinimumSwapstoMakeStringsEqual {

  public int minimumSwap(String s1, String s2) {
    int ans = 0;
    int xy = 0;
    int yx = 0;

    for (int i = 0; i < s1.length(); i++) {
      if (s1.charAt(i) == 'x' && s2.charAt(i) == 'y') {
        xy++;
      } else if (s1.charAt(i) == 'y' && s2.charAt(i) == 'x') {
        yx++;
      }
    }

    ans += xy / 2;
    ans += yx / 2;


    if (xy % 2 != yx % 2) {
      return -1;
    } else if (xy % 2 == 1) {
      System.out.println("xy:" + xy + ", yx:" + yx);
      ans += 2;
    }

    return ans;
  }

  @Test
  public void test1() {

    String s1 = "xx";
    String s2 = "yy";

    int result = minimumSwap(s1, s2);

    Assert.assertEquals(1, result);
  }

  @Test
  public void test2() {

    String s1 = "xy";
    String s2 = "yx";

    int result = minimumSwap(s1, s2);

    Assert.assertEquals(2, result);
  }

  @Test
  public void test3() {

    String s1 = "xx";
    String s2 = "xy";

    int result = minimumSwap(s1, s2);

    Assert.assertEquals(-1, result);
  }

  @Test
  public void test4() {

    String s1 = "xxyyxyxyxx";
    String s2 = "xyyxyxxxyx";

    int result = minimumSwap(s1, s2);

    Assert.assertEquals(4, result);
  }
}
