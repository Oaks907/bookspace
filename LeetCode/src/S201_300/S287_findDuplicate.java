package S201_300;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/find-the-duplicate-number/
 * Create by haifei on 22/10/2019 11:11 PM.
 */
public class S287_findDuplicate {
  public int findDuplicate(int[] nums) {
    Arrays.sort(nums);

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == nums[i - 1]) {
        return nums[i];
      }
    }

    return -1;
  }
}
