package S701_800;

import java.time.temporal.ValueRange;

/**
 * Create by haifei on 4/1/2018.
 */
public class S724_FindPivotIndex {

  public static void main(String[] args) {
    int nums1[] = {1,7,3,6,5,6};
    int nums2[] = {-1,-1, -1,-1,-1,0};
    System.out.println(pivotIndex(nums1));
    System.out.println(pivotIndex(nums2));

  }

  public static int pivotIndex(int[] nums) {
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
    }

    int half = 0;
    for (int i = 0; i < nums.length; i++) {
      if (half * 2 == sum - nums[i]) {
        return i;
      }
      half += nums[i];
    }
    return -1;
  }
}
