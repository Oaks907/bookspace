package contest.contest_166;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 8/12/2019 10:37 AM.
 */
public class SubtracttheProductandSumofDigitsofanInteger {

  public int subtractProductAndSum(int n) {

    int sum = 0;
    int product = 1;

    String[] split = String.valueOf(n).split("");
    for (int i = 0; i < split.length; i++) {
      int num = Integer.parseInt(split[i]);
      sum += num;
      product *= num;
    }

    return product - sum;
  }

  @Test
  public void test() {
    int result = subtractProductAndSum(234);

    Assert.assertEquals(15, result);
  }

  @Test
  public void test1() {
    int result = subtractProductAndSum(4421);

    Assert.assertEquals(21, result);
  }

}
