package S201_300;

/**
 * Create by haifei on 28/8/2019 12:39 AM.
 */
public class S238_ProductofArrayExceptSelf {

  public int[] productExceptSelf(int[] nums) {
    int length = nums.length;
    int[] temp = new int[length];
    temp[0] = 1;
    for (int i = 1; i < nums.length; i++) {
      temp[i] = temp[i - 1] * nums[i - 1];
    }

    int right = 1;
    for (int i = length - 1; i >= 0; i--) {
      temp[i] *= right;
      right *= nums[i];
    }

    return temp;
  }

}
