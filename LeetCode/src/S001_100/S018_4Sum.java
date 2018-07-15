package S001_100;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create by haifei on 6/2/2018.
 */
public class S018_4Sum {
  public static List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> result = new ArrayList<>();

    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 3; i++) {
      for (int j = i + 1; j < nums.length - 2; j++){
        int left = j + 1;
        int right = nums.length - 1;
        while (left < right) {
          List<Integer> list = new ArrayList<>();
          int value = nums[i] + nums[j] + nums[left] + nums[right];
          if (value == target) {
            list.add(nums[i]);
            list.add(nums[j]);
            list.add(nums[left]);
            list.add(nums[right]);
            result.add(list);
          } else if (value < target) {
            while (left < right && nums[left] == nums[left + 1]){
              left++;
            }
            left++;
          } else if (value > target) {
            while (left < right && nums[right] == nums[right - 1]) {
              right--;
            }
            right--;
          }
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int[] nums = {1, 0, -1, 0, -2, 2};
    System.out.println(fourSum(nums, 0));
  }
}
