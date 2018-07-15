package S601_700;

/**
 * Create by haifei on 16/1/2018.
 */
public class S665_NonDecreasiongArray {
  public static boolean checkPossibility(int[] nums) {
    if (nums.length == 1) {
      return true;
    }

    int motify = 0;

    for (int i = 1, prev = nums[0]; i < nums.length; i++) {
      if (prev > nums[i]) {
        if (motify++ > 0) return false;
        if (i - 2 >= 0 && nums[i - 2] > nums[i]) {
          continue;
        }
      }
      prev = nums[i];
    }
    return true;
  }

  public static void main(String[] args) {
    int[] nums = {4, 2, 3}; //true
    int[] nums1 = {1, 5, 3, 4}; //true
    int[] nums2 = {1, 1, 1};  //true
    int[] nums3 = {1, 1, 5, 2, 5}; //false
    int[] nums4 = {2, 3, 3, 2, 4}; //true
//    System.out.println(checkPossibility(nums));
//    System.out.println(checkPossibility(nums1));
//    System.out.println(checkPossibility(nums2));
    System.out.println(checkPossibility(nums3));
//    System.out.println(checkPossibility(nums4));
  }
}
