package S501_600;

import java.util.Arrays;

/**
 * Create by haifei on 5/1/2018.
 */
public class S561_ArrayPartition_I {

  public static void main(String[] args) {
    int nums1[] = {1, 4, 3, 2};
    System.out.println(arrayPairSum(nums1));
  }

  private static int arrayPairSum(int[] nums) {
    Arrays.sort(nums);
    int n = nums.length;
    int result = 0;
    int i = 0;

    while (i < n) {
      result += Math.min(nums[i] , nums[i]);
      i += 2;
    }
    return result;
  }
}
