package S101_200;

/**
 * Create by haifei on 24/1/2018.
 */
public class S189_RotateArray {

  public static void rotate(int[] nums, int k) {
    int[] tempArray = new int[k];
    int count = 0;
    for (int i = nums.length - k; i < nums.length; i++) {
      tempArray[count++] = nums[i];
    }

    for (int i = nums.length - k - 1; i >= 0; i--) {
      nums[i + k] = nums[i];
    }

    for (int i = 0; i < k; i++) {
      nums[i] = tempArray[i];
    }

    for (int i : nums) {
      System.out.print(i + " ");
    }
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 3, 4, 5, 6, 7};
    //rotate(nums, 3);
    rotate(new int[]{-1}, 2);
  }
}
