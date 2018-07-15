package S001_100;

import java.util.Arrays;

public class S016_3SumClosest {
  public static int threeSumClosest(int[] nums, int target) {

    Arrays.sort(nums);
    int result = nums[0] + nums[1] + nums[3];
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1, k = nums.length - 1; j < k; ){
        int sum = nums[i] + nums[j] + nums[k];
        if (sum > target) {
          k--;
        } else {
          j++;
        }

        if (Math.abs(sum - target) < Math.abs(result - target)) {
          result = sum;
        }
      }
    }

      return result;
  }

  public static void main(String[] args) {
//    int nums[] = {-1, 2, 1, -4}; // 2
//    int nums1[] = {0, 1 , 2, -3}; //
//    System.out.println(threeSumClosest(nums, 1));

    int nums2[] = {-1, -1, 1, 1, 3};
    System.out.println(threeSumClosest(nums2, -1));
  }
}
