package S001_100;

import org.junit.Test;
import utils.PrintUtils;

/**
 * Create by haifei on 31/12/2019 12:47 PM.
 */
public class S034_FindFirstandLastPositionofElementinSortedArrayMedium {

  public int[] searchRange(int[] nums, int target) {

    int start = firstGreaterEqual(nums, target);
    if (start == nums.length || nums[start] != target) {
      return new int[]{-1, -1};
    }
    return new int[]{start, firstGreaterEqual(nums, target + 1) - 1};
  }

  //find the first number that is greater than or equal to target.
  //could return A.length if target is greater than A[A.length-1].
  //actually this is the same as lower_bound in C++ STL.
  private int firstGreaterEqual(int[] A, int target) {
    int left = 0;
    int right = A.length;

    while (left < right) {
      int mid = left + ((right - left) >> 1);

      if (A[mid] < target) {
        left = mid + 1;
      } else {
        //should not be mid-1 when A[mid]==target.
        //could be mid even if A[mid]>target because mid<high.
        right = mid;
      }
    }

    return left;
  }

  @Test
  public void test() {

    int[] nums = {5, 7, 7, 8, 8, 10};

    PrintUtils.printArray(searchRange(nums, 6));
  }
}
