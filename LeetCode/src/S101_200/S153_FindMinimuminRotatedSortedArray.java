package S101_200;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 19/1/2020 2:45 AM.
 */
public class S153_FindMinimuminRotatedSortedArray {

  public int findMin(int[] nums) {

    int left = 0;
    int right = nums.length - 1;
    while (left < right) {
      int mid = left + (right - left) / 2;

      if (nums[mid] > nums[right]) {
        left = mid + 1;
      } else if (nums[mid] < nums[left]) {
        right = mid;
      } else {
        return nums[left];
      }
    }

    return nums[left];
  }

  @Test
  public void test() {

    int[] arr = {3, 4, 5, 1, 2};

    int result = findMin(arr);

    Assert.assertEquals(1, result);
  }

  @Test
  public void test1() {
    int[] arr = {4, 5, 6, 7, 0, 1, 2};

    int result = findMin(arr);

    Assert.assertEquals(0, result);
  }
}
