package S001_100;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 17/1/2020 7:39 PM.
 */
public class S080_RemoveDuplicatesfromSortedArrayII {

  public int removeDuplicates(int[] nums) {

    int left;
    int right;
    int result = nums.length;

    for (int i = 1; i < nums.length; i++) {
      left = i - 1;
      right = i;

      while (right < result && nums[left] == nums[right]) {
        right++;
      }

      int cha = (right - 1) - left - 1;

      if (cha < 1) {
        i = right;
        continue;
      }

      result -= cha;

      for (int j = left + 2; j < result; j++) {
        nums[j] = nums[j + cha];
        nums[j + cha] = -1;
      }

      i = left + 1;
    }

//    PrintUtils.printArray(nums);

    return result;
  }

  public int removeDuplicates2(int[] nums) {
    int i = 0;
    for (int n : nums)
      if (i < 2 || n > nums[i - 2])
        nums[i++] = n;
    return i;
  }

  @Test
  public void test() {

    int[] arr = {1, 1, 1, 2, 2, 3};

    int result = removeDuplicates(arr);

    Assert.assertEquals(5, result);
  }

  @Test
  public void test1() {

    int[] arr = {0, 0, 1, 1, 1, 1, 2, 3, 3};

    int result = removeDuplicates(arr);

    Assert.assertEquals(7, result);
  }
}
