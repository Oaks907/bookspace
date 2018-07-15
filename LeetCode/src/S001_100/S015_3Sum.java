package S001_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create by haifei on 4/2/2018.
 */
public class S015_3Sum {

  /**
   * 0000的情况无法排除
   *
   * @param nums
   * @return
   */
  public static List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();

    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++) {
      if (i - 1 >= 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      for (int j = i + 1; j < nums.length; j++) {
        int value = nums[i] + nums[j];
        if(value > 0) {
          break;
        }
        for (int k = j + 1; k < nums.length; k++) {
          if (value + nums[k] == 0) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[i]);
            list.add(nums[j]);
            list.add(nums[k]);
            result.add(list);
          }
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int nums[] = {-1, 0, 1, 2, -1, -4};
    System.out.println(threeSum(nums));
    System.out.println(threeSum2(nums));
  }

  public static List<List<Integer>> threeSum2(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();

    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++) {
      if (i - 1 >= 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      for (int j = i + 1,  k = nums.length - 1; j < k;) {
        int value = nums[i] + nums[j] + nums[k];
        if (value == 0) {
          result.add(Arrays.asList(nums[i], nums[j], nums[k]));
          j++;
          k--;
          while (j < k && nums[j] == nums[j-1]) j++;
          while (j < k && nums[k] == nums[k+1]) k--;
        } else if (value > 0){
          k--;
        } else if (value < 0) {
          j++;
        }
      }
    }
    return result;
  }
}























