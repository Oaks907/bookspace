package S401_500;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/teemo-attacking/
 * Create by haifei on 25/10/2019 12:12 AM.
 */
public class S495_TeemoAttacking {

  public int findPoisonedDuration(int[] timeSeries, int duration) {

    int result = 0;

    for (int i = 0; i < timeSeries.length - 1; i++) {
      if (timeSeries[i] + duration < timeSeries[i + 1]) {
        result += duration;
      } else {
        result += timeSeries[i + 1] - timeSeries[i];
      }
    }
    if (timeSeries.length > 0) {
      result += duration;
    }

    return result;
  }

  @Test
  public void test() {
    int[] timeSeries = {1, 4};

    int result = findPoisonedDuration(timeSeries, 2);

    Assert.assertEquals(4, result);
  }

  @Test
  public void test1() {
    int[] timeSeries = {1, 2};

    int result = findPoisonedDuration(timeSeries, 2);

    Assert.assertEquals(3, result);
  }

  @Test
  public void test2() {
    int[] timeSeries = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    int result = findPoisonedDuration(timeSeries, 1);

    Assert.assertEquals(9, result);
  }

  @Test
  public void test3() {
    int[] timeSeries = {1, 3, 5, 7, 9, 11, 13, 15};

    int result = findPoisonedDuration(timeSeries, 2);

    Assert.assertEquals(16, result);
  }
}
