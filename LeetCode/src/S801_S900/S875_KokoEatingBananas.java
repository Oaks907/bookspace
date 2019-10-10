package S801_S900;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 10/10/2019 8:23 AM.
 */
public class S875_KokoEatingBananas {

  public int minEatingSpeed(int[] piles, int H) {
    int left = 1, right = 1000000000;

    while (left < right) {
      int mid = (left + right) / 2;
      int total = 0;

      for (int pile : piles) {
        total += (pile + mid - 1) / mid;
      }

      if (total > H) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    return left;
  }

  @Test
  public void test() {
    int[] A = {3, 6, 7, 11};
    int result = minEatingSpeed(A, 8);

    Assert.assertEquals(4, result);
  }

  @Test
  public void test1() {
    int[] A = {30, 11, 23, 4, 20};
    int result = minEatingSpeed(A, 5);

    Assert.assertEquals(30, result);
  }

  @Test
  public void test2() {
    int[] A = {30, 11, 23, 4, 20};
    int result = minEatingSpeed(A, 6);

    Assert.assertEquals(23, result);
  }
}
