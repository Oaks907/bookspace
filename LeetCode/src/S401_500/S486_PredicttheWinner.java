package S401_500;

import utils.PrintUtils;

import java.io.FileReader;

/**
 * Create by haifei on 17/1/2019 11:45 PM.
 * <p>
 * https://leetcode.com/problems/predict-the-winner/
 */
public class S486_PredicttheWinner {

  public boolean PredictTheWinner(int[] nums) {

    int len = nums.length;
    int[][] dp = new int[len][len];

    for (int i = 0; i < len; i++) {
      dp[i][i] = nums[i];
    }

    for (int i = 1; i < len; i++) {
      for (int j = 0; j + i < len; j++) {
        dp[j][j + i] = Math.max(nums[j + i] - dp[j][j + i - 1], nums[j] - dp[j + 1][j + i]);
      }
    }

    return dp[0][len - 1] >= 0;
  }

  // 递归
  public boolean PredictTheWinner2(int[] nums) {
    return findAns(nums, 0, nums.length - 1) >= 0;
  }

  public int findAns(int[] nums, int i, int j) {
    if (i == j) {
      return nums[i];
    } else {
      int first = nums[i] - findAns(nums, i + 1, j);
      int last = nums[j] - findAns(nums, i, j - 1);
      return Math.max(first, last);
    }
  }

  public static void main(String[] args) {

    final S486_PredicttheWinner predicttheWinner = new S486_PredicttheWinner();

    int[] nums = {1, 5, 2};
//    System.out.println(predicttheWinner.PredictTheWinner(nums));

    nums = new int[]{1, 5, 233, 7};
    System.out.println(predicttheWinner.PredictTheWinner(nums));
  }
}
