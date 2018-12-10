package S201_300;

import java.util.Arrays;

/**
 * Create by haifei on 10/12/2018 11:08 PM.
 * <p>
 * https://leetcode.com/problems/longest-increasing-subsequence/
 */
public class S300_LongestIncreasingSubsequence {

  public int lengthOfLIS(int[] nums) {
    if (null == nums || nums.length == 0) {
      return 0;
    }
    int[] dp = new int[nums.length];
    dp[0] = 1;
    int maxans = 1;

    for (int i = 1; i < nums.length; i++) {
      int count = 0;
      for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i]) {
          count = Math.max(count, dp[j]);
        }
      }
      dp[i] = count + 1;
      maxans = Math.max(dp[i], maxans);
    }

    return maxans;
  }

  public static void main(String[] args) {
    final S300_LongestIncreasingSubsequence subsequence =
      new S300_LongestIncreasingSubsequence();

    int[] ints = {10, 9, 2, 5, 3, 7, 101, 18};
    System.out.println(subsequence.lengthOfLIS(ints));

    ints = new int[]{4, 10, 4, 3, 8, 9};
    System.out.println(subsequence.lengthOfLIS(ints));
  }
}
