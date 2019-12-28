package S901_S1000;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 28/12/2019 6:54 PM.
 */
public class S926_FlipStringMonotoneIncreasing {

  public int minFlipsMonoIncr(String S) {

    String[] split = S.split("");

    int flipCount = 0;
    int oneCount = 0;

    for (int i = 0; i < split.length; i++) {
      if (split[i].equals("0")) {
        if (oneCount == 0) {
          continue;
        } else {
          flipCount++;
        }
      } else {
        oneCount++;
      }

      if (flipCount > oneCount) {
        flipCount = oneCount;
      }
    }

    return flipCount;
  }


  @Test
  public void test() {

    int result = minFlipsMonoIncr("00110");

    Assert.assertEquals(1, result);
  }


  @Test
  public void test1() {

    int result = minFlipsMonoIncr("010110");

    Assert.assertEquals(2, result);
  }

  @Test
  public void test2() {

    int result = minFlipsMonoIncr("00011000");

    Assert.assertEquals(2, result);
  }

  @Test
  public void test3() {

    int result = minFlipsMonoIncr("10011111110010111011");

    Assert.assertEquals(5, result);
  }

  @Test
  public void test4() {

    int result = minFlipsMonoIncr("100110");

    Assert.assertEquals(2, result);
  }
}
