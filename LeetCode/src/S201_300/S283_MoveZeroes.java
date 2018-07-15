package S201_300;

/**
 * Create by haifei on 28/1/2018.
 */
public class S283_MoveZeroes {
  public void moveZeroes(int[] nums) {

    int zeroSize = 0;
    for (int i = 0; i < nums.length; i++) {
       if (nums[i] == 0) {
         zeroSize++;
       } else {
         nums[i - zeroSize] = nums[i];
       }
    }

    for (int i = nums.length - 1; i >= nums.length - zeroSize; i--) {
      nums[i] = 0;
    }
  }
}
