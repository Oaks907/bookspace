package S001_100;

/**
 * Create by haifei on 3/2/2018.
 */
public class S026_RemoveDuplicatesFromSortedArray {
  public int removeDuplicates(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int result = 0;
    for (int i = 0; i < nums.length; i++) {
        if (nums[result] != nums[i]){
          nums[++result] = nums[i];
        }
    }
    return ++result;
  }
}
