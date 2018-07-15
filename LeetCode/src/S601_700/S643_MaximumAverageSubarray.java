package S601_700;

/**
 * Create by haifei on 18/1/2018.
 */
public class S643_MaximumAverageSubarray {
  public static double findMaxAverage(int[] nums, int k) {
    int kAllValue = 0;
    if (k > nums.length) {
      return 0;
    }
    for (int i = 0; i < k; i++) {
      kAllValue += nums[i];
    }
    double result = (double) kAllValue / k;
    for (int i = k; i < nums.length; i++) {
      kAllValue = kAllValue + nums[i] - nums[i - k];
      double val = (double) kAllValue / k;
      result = Math.max(result, val);
    }
    return result;
  }

  public static void main(String[] args) {
    int[] nums = {1, 12, -5, -6, 50, 3};
    System.out.println(findMaxAverage(nums, 4));
  }
}
