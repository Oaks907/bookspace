package S301_400;

/**
 * Create by haifei on 30/11/2018 9:04 PM.
 * <p>
 * https://leetcode.com/problems/range-sum-query-immutable/
 */
public class S303_RangeSumQuery {

  private int[] dp;

  public S303_RangeSumQuery(int[] nums) {

    dp = new int[nums.length + 1];

    for (int i = 0; i < nums.length; i++) {
      dp[i + 1] = nums[i] + dp[i];
    }
  }

  public int sumRange(int i, int j) {
    return dp[j + 1] - dp[i];
  }
}
