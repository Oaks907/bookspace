package S1101_S1200;

import org.junit.Assert;
import org.junit.Test;
import utils.PrintUtils;

/**
 * https://leetcode.com/problems/grumpy-bookstore-owner/
 * Create by haifei on 24/10/2019 11:32 PM.
 */
public class S1052_GrumpyBookstoreOwner {

  public int maxSatisfied(int[] customers, int[] grumpy, int X) {

    int[] satisfied = new int[customers.length];

    for (int i = 0; i < customers.length; i++) {
      satisfied[i] = (grumpy[i] == 0 ? customers[i] : 0) + (i == 0 ? 0 : satisfied[i - 1]);
    }

    int result = satisfied[customers.length - 1];

    for (int i = 0; i < customers.length; i++) {
      if (grumpy[i] == 0) {
        continue;
      }

      int value = 0;
      for (int j = i; j < i + X && j < customers.length; j++) {
        if (grumpy[j] == 1) {
          value += customers[j];
        }
      }

      result = Math.max(result, satisfied[customers.length - 1] + value);
    }

    return result;
  }

  @Test
  public void test() {

    int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
    int[] grumpys = {0, 1, 0, 1, 0, 1, 0, 1};
    int result = maxSatisfied(customers, grumpys, 3);

    Assert.assertEquals(16, result);
  }

  @Test
  public void test1() {
    int[] customers = {8, 8};
    int[] grumpys = {1, 0};
    int result = maxSatisfied(customers, grumpys, 2);

    Assert.assertEquals(16, result);
  }
}
