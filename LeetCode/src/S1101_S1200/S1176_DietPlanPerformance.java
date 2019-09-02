package S1101_S1200;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 1/9/2019 11:03 AM.
 */
public class S1176_DietPlanPerformance {

  public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {

    int result = 0;

    for (int low = -1, high = 0, win = 0; high < calories.length; high++) {
      win += calories[high];
      if (high - low > k) {
        win -= calories[++low];
      }
      if (high - low < k) {
        continue;
      }
      if (win < lower) {
        result--;
      }
      if (win > upper) {
        result++;
      }
    }

    return result;
  }

  @Test
  public void test1() {
    S1176_DietPlanPerformance performance = new S1176_DietPlanPerformance();
    int[] nums = {1, 2, 3, 4, 5};
    Assert.assertEquals(0, performance.dietPlanPerformance(nums, 1, 3, 3));
    nums = new int[]{6, 5, 0, 0};
    Assert.assertEquals(0, performance.dietPlanPerformance(nums, 2, 1, 5));

    nums = new int[]{6, 13, 8, 7, 10, 1, 12, 11};
    Assert.assertEquals(3, performance.dietPlanPerformance(nums, 6, 5, 37));
  }
}
