package S001_100;

/**
 * Create by haifei on 3/1/2018.
 */
public class S053_MaximumSubarray {

  public static void main(String[] args) {

    int[] num1 = {-1};
    int[] num2 = {-2,1,-3,4,-1,2,1,-5,4};
    int[] num3 = {-1, -3, -2, 0, -1};

    System.out.println(maxSubArray(num1));
    System.out.println(maxSubArray(num2));
    System.out.println(maxSubArray(num3));
  }

  public static int maxSubArray(int[] nums) {
    int sum = 0;
    int result = nums[0];
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      if (sum >= result) {
        result = sum;
      }
      if (sum < 0){
        if (sum > result) {
          result = sum;
        }
        sum = 0;
      }
    }
    return result;
  }
}
