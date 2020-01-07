package S001_100;

import org.junit.Assert;
import org.junit.Test;
import utils.PrintUtils;

/**
 * Create by haifei on 7/1/2020 6:52 PM.
 */
public class S055_JumpGame {

  public boolean canJump(int[] nums) {

    int shortCanJumpIndex = nums.length - 1;
    boolean[] dp = new boolean[nums.length];
    dp[shortCanJumpIndex] = true;

    for (int i = nums.length - 2; i >= 0; i--) {

      int jumpLen = nums[i];

      if (jumpLen + i >= shortCanJumpIndex) {
        dp[i] = true;
        shortCanJumpIndex = i;
      }
    }

//    PrintUtils.printArray(dp);

    return dp[0];
  }

  @Test
  public void test() {
    int[] arr = {2, 3, 1, 1, 4};

    boolean result = canJump(arr);

    Assert.assertTrue(result);
  }

  @Test
  public void test1() {

    int[] arr = {3, 2, 1, 0, 4};

    boolean result = canJump(arr);

    Assert.assertFalse(result);
  }
}
