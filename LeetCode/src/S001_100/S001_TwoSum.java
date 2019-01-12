package S001_100;

/**
 * Create by haifei on 23/1/2018.
 */
public class S001_TwoSum {
  public static int[] twoSum(int[] nums, int target) {
    int[] result = new int[2];

    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        int val = nums[i] + nums[j];
        if (val == target) {
          result[0] = i;
          result[1] = j;
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int nums[] = {2, 7, 11, 15};
  }
}
