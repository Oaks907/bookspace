package S301_400;

/**
 * Create by haifei on 22/1/2019 9:18 AM.
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 */
public class S334_IncreasingTripletSubsequence {

  /**
   * 时间复杂度 n * logN
   * 空间复杂度 o(n)
   *
   * @param nums
   * @return
   */
  public boolean increasingTriplet(int[] nums) {
    if (nums == null || nums.length < 3) {
      return false;
    }

    int dp[] = new int[nums.length];

    for (int i = 0; i < nums.length; i++) {
      int count = 0;

      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          count = Math.max(count, dp[j]);
        }
      }

      dp[i] = count + 1;
      if (dp[i] >= 3) {
        return true;
      }
    }

    return false;
  }

  public boolean increasingTriplet_best(int[] nums) {

    if (nums == null || nums.length < 3) {
      return false;
    }

    int small = Integer.MAX_VALUE;
    int big = Integer.MAX_VALUE;

    for (int i = 0; i < nums.length; i++) {
      int val = nums[i];
      if (val <= small) {
        small = val;
      } else if (val <= big) {
        big = val;
      } else {
        //small < val && val > big --> small < big < val
        return true;
      }
    }

    return false;
  }


  public static void main(String[] args) {

  }
}
