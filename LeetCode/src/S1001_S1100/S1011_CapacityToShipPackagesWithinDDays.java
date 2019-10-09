package S1001_S1100;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 9/10/2019 11:30 PM.
 */
public class S1011_CapacityToShipPackagesWithinDDays {

  public int shipWithinDays(int[] weights, int D) {
    int left = 0, right = 0;

    for (int weight : weights) {
      left = Math.max(weight, left);
      right += weight;
    }

    while (left < right) {
      int mid = (left + right) / 2;
      int need = 1;
      int cur = 0;

      for (int weight : weights) {
        if (cur + weight > mid) {
          need += 1;
          cur = 0;
        }
        cur += weight;
      }

      if (need > D) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    return left;
  }

  @Test
  public void test() {
    int[] A = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int result = shipWithinDays(A, 5);
    Assert.assertEquals(15, result);
  }

  @Test
  public void test1() {
    int[] A = {3, 2, 2, 4, 1, 4};
    int result = shipWithinDays(A, 3);
    Assert.assertEquals(6, result);
  }

  @Test
  public void test2() {
    int[] A = {1, 2, 3, 1, 1};
    int result = shipWithinDays(A, 4);
    Assert.assertEquals(3, result);
  }
}
