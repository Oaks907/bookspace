package S701_800;

/**
 * Create by haifei on 3/1/2018.
 */
public class S748_LargestNumberAtLeastTwiceOfOther {

  public int dominantIndex(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int largerst = 0, lower = 0, result = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] >= largerst) {
        lower = largerst;
        largerst = nums[i];
        result = i;
      } else if (lower < nums[i]){
        lower = nums[i];
      }
    }
    return largerst >= lower * 2 ? result : -1;
  }
}
