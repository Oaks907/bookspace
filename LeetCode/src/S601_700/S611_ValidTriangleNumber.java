package S601_700;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Create by haifei on 7/1/2020 8:28 PM.
 */
public class S611_ValidTriangleNumber {

  // Slow
  public int triangleNumber(int[] nums) {

    int count = 0;
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        for (int k = j + 1; k < nums.length; k++) {
          if (isValidTriangle(nums[i], nums[j], nums[k])) {
            count++;
          }
        }
      }
    }

    return count;
  }

  private boolean isValidTriangle(int a, int b, int c) {
    return a + b > c && a + c > b && b + c > a;
  }

  public int triangleNumber2(int[] nums) {

    Arrays.sort(nums);
    int count = 0;
    int n = nums.length;

    for (int i = n - 1; i >= 2; i--) {
      int left = 0;
      int right = i - 1;

      while (left < right) {
        if (nums[left] + nums[right] > nums[i]) {
          count += right - left;
          right--;
        } else {
          left++;
        }
      }
    }

    return count;
  }

  @Test
  public void test() {
    int[] arr = {2, 2, 3, 4};

    int result = triangleNumber(arr);

    Assert.assertEquals(3, result);

    result = triangleNumber2(arr);

    Assert.assertEquals(3, result);
  }
}
