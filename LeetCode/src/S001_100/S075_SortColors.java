package S001_100;

import org.junit.Test;
import utils.PrintUtils;

/**
 * https://leetcode.com/problems/sort-colors/
 * Create by haifei on 3/1/2020 6:31 PM.
 */
public class S075_SortColors {

  public void sortColors(int[] nums) {
    int oneCount = 0;
    int twoCount = 0;
    int zeroCount = 0;

    for (int num : nums) {
      if (num == 0) {
        zeroCount++;
      } else if (num == 1) {
        oneCount++;
      } else {
        twoCount++;
      }
    }

    for (int i = 0; i < nums.length; i++) {
      if (i < zeroCount) {
        if (nums[i] == 0) {
          continue;
        }
        nums[i] = 0;
      } else if (i < oneCount + zeroCount) {
        if (nums[i] == 1) {
          continue;
        }
        nums[i] = 1;
      } else if (i < zeroCount + oneCount + twoCount) {
        if (nums[i] == 2) {
          continue;
        }
        nums[i] = 2;
      }
    }

  }

  @Test
  public void test() {
    int[] arr = {2, 0, 2, 1, 1, 0};

    sortColors(arr);

    PrintUtils.printArray(arr);
  }
}
