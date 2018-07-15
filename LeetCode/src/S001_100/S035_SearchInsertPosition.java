package S001_100;

/**
 * Create by haifei on 28/1/2018.
 */
public class S035_SearchInsertPosition {
  public static int searchInsert(int[] nums, int target) {
    int result = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == target) {
        return i;
      } else if (nums[i] > target) {
        result = i;
        break;
      }
      if (i == nums.length - 1) {
        return nums.length;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int[] nums = {1, 3, 5, 6};
    int[] nums2 = {1, 3, 5, 6};
    System.out.println(searchInsert(nums, 5)); // 2
    System.out.println(searchInsert(nums, 2)); // 1
    System.out.println(searchInsert(nums, 7)); // 4
  }
}
