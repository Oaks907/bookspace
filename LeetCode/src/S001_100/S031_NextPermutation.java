package S001_100;

/**
 * Create by haifei on 3/9/2019 11:58 PM.
 */
public class S031_NextPermutation {

  public void nextPermutation(int[] nums) {

    int length = nums.length, i;

    for (i = length - 1; i > 0; i--) {
      if (nums[i] > nums[i - 1]) {
        int temp = length;
        while (nums[i - 1] >= nums[--temp]) {

        }
        swap(nums, temp, i - 1);
        break;
      }
    }
    reverse(nums, i, length - 1);
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }

  private void reverse(int[] nums, int i, int j) {
    while (i < j) {
      swap(nums, i, j);
      i++;
      j--;
    }
  }
}
