package S001_100;

/**
 * Create by haifei on 22/1/2018.
 */
public class S027_RemoveElement {
  public int removeElement(int[] nums, int val) {
    int count = nums.length;
    int index = 0;
    for (int value : nums) {
      if (value == val) {
        count--;
      } else {
        nums[index++] = value;
      }
    }
    return count;
  }
}
