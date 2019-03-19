package S101_200;

import utils.PrintUtils;

/**
 * Create by haifei on 21/1/2019 9:16 AM.
 * Given an integer array nums, find the contiguous subarray within an array (containing at least
 * one number) which has the largest product.
 * https://leetcode.com/problems/maximum-product-subarray/
 */
public class S152_MaximumProductSubarray {

  public int maxProduct(int[] nums) {

    if (nums == null || 0 == nums.length) {
      return 0;
    }

    int[][] dp = new int[2][nums.length];
    dp[0][0] = nums[0];
    dp[1][0] = nums[0];

    int max = nums[0];

    for (int i = 1; i < nums.length; i++) {
      int a = dp[0][i - 1] * nums[i];
      int b = dp[1][i - 1] * nums[i];

      dp[0][i] = Math.max(a, Math.max(b, nums[i]));
      dp[1][i] = Math.min(a, Math.min(b, nums[i]));

      max = Math.max(max, dp[0][i]);
    }

    PrintUtils.printArray(dp);

    return max;
  }

  public static void main(String[] args) {
    final S152_MaximumProductSubarray maximumProductSubarray =
      new S152_MaximumProductSubarray();

    int[] nums = {-1, 0, -2};  //0
    System.out.println(maximumProductSubarray.maxProduct(nums));

    nums = new int[]{2, 3, -2, 4}; //6
    System.out.println(maximumProductSubarray.maxProduct(nums));

    nums = new int[]{-1};
    System.out.println(maximumProductSubarray.maxProduct(nums));

    nums = new int[]{2, -5, -2, -4, 3};
    System.out.println(maximumProductSubarray.maxProduct(nums));
  }
}
