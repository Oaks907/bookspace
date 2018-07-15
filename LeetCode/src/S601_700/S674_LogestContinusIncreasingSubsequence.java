package S601_700;

/**
 * Create by haifei on 15/1/2018.
 */
public class S674_LogestContinusIncreasingSubsequence {
  public static int findLengthOfLCIS(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int left = 0;
    int right = left + 1;
    int result = 0;
    int temp = 1;
    while (right < nums.length) {
      if (nums[left] < nums[right]) {
        temp++;
      } else {
        result = Math.max(temp, result);
        temp = 1;
      }
      left++;
      right++;
    }
    return Math.max(temp, result);
  }

  public static void main(String[] args) {
    int[] nums = {1, 3, 5, 4, 7};
    int[] nums1 = {2, 2, 2, 2, 2};
    int[] nums2 = {1, 3, 5, 7};
    int[] nums3 = {1, 3, 5, 4, 2, 3, 4, 5};
    System.out.println(findLengthOfLCIS(nums));
    System.out.println(findLengthOfLCIS(nums1));
    System.out.println(findLengthOfLCIS(nums2));
    System.out.println(findLengthOfLCIS(nums3));
  }
}
