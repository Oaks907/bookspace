package contest.contest_153;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 8/9/2019 11:28 AM.
 */
public class MaximumSubarraySumwithOneDeletion {

  public int maximumSum(int[] arr) {
    int res = arr[0];
    int dp = arr[0];

    int dpExclude = Integer.MIN_VALUE;
    for (int i = 1; i < arr.length; i++) {

      dpExclude = (dpExclude == Integer.MIN_VALUE) ? Math.max(dp, arr[i]) : Math.max(dp,
        dpExclude + arr[i]);
      dp = Math.max(dp + arr[i], arr[i]);

      res = Math.max(dp, res);
      res = Math.max(res, dpExclude);
    }

    return res;
  }

  public int maximumSum1(int[] arr) {
    if (arr == null || arr.length == 0) return 0;

    // dp[i][0] when it comes to arr[i], with no deletion
    // dp[i][1] when it comes to arr[i], with one deletion
    // dp[i][0] = max(dp[i - 1][0] + arr[i], arr[i])
    // dp[i][1] = max(dp[i - 1][1] + arr[i], dp[i - 1][0])

    int[][] dp = new int[arr.length][2];
    int max = arr[0];
    dp[0][0] = arr[0];
    dp[0][1] = Math.max(0, arr[0]);
    for (int i = 1; i < arr.length; i++) {
      dp[i][0] = Math.max(dp[i - 1][0] + arr[i], arr[i]);
      dp[i][1] = Math.max(dp[i - 1][1] + arr[i], dp[i - 1][0]);
      max = Math.max(max, dp[i][0]);
      max = Math.max(max, dp[i][1]);
    }
    return max;
  }

  @Test
  public void test1() {

    MaximumSubarraySumwithOneDeletion deletion =
      new MaximumSubarraySumwithOneDeletion();
    int[] arr = {1, -2, 0, 3};
    int result = deletion.maximumSum(arr);

    Assert.assertEquals(4, result);
  }

  @Test
  public void test2() {
    MaximumSubarraySumwithOneDeletion deletion =
      new MaximumSubarraySumwithOneDeletion();
    int[] arr = {1, -2, -2, 3};
    int result = deletion.maximumSum(arr);

    Assert.assertEquals(3, result);
  }

  @Test
  public void test3() {
    MaximumSubarraySumwithOneDeletion deletion =
      new MaximumSubarraySumwithOneDeletion();
    int[] arr = {-1, -1, -1, -1};
    int result = deletion.maximumSum(arr);

    Assert.assertEquals(-1, result);
  }

  @Test
  public void test4() {
    MaximumSubarraySumwithOneDeletion deletion =
      new MaximumSubarraySumwithOneDeletion();
    int[] arr = {1, -4, -5, -2, 5, 0, -1, 2};
    int result = deletion.maximumSum(arr);

    Assert.assertEquals(7, result);
  }
}
