package S101_200;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 18/1/2020 9:20 PM.
 */
public class S162_FindPeakElement {

  public int findPeakElement(int[] nums) {

    int left = 0, right = nums.length - 1;

    int len = nums.length;

    if (len > 1 && nums[left] > nums[1]) {
      return 0;
    }

    if (len > 1 && nums[right] > nums[right - 1]) {
      return right;
    }

    while (right - left > 1) {

      int mid = left + (right - left) / 2;

      if (nums[mid] < nums[mid + 1]) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    return (left == len - 1 || nums[left] > nums[left + 1]) ? left : right;
  }

  @Test
  public void test() {

    int[] arr = {1, 2, 1, 3, 5, 6, 4};

    System.out.println(findPeakElement(arr));
  }

  @Test
  public void test1() {

    int[] arr = {1, 2, 3, 1};

    int result = findPeakElement(arr);

    Assert.assertEquals(2, result);
  }
}
