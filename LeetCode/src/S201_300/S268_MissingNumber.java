package S201_300;

/**
 * Create by haifei on 28/1/2018.9
 */
public class S268_MissingNumber {
  public int missingNumber(int[] nums) {
    int result = nums.length;
    for (int i = 0; i < nums.length; i++) {
      result += i - nums[i];
    }
    return result;
  }
}
