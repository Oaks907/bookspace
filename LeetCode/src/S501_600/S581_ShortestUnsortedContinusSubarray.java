package S501_600;

import java.util.Arrays;

/**
 * Create by haifei on 22/1/2018.
 */
public class S581_ShortestUnsortedContinusSubarray {
  public static int findUnsortedSubarray(int[] nums) {
    int[] numsCopy = Arrays.copyOf(nums, nums.length);
    Arrays.sort(numsCopy);
    int start = 0;
    int end = numsCopy.length;

    for (int i = 0; i < nums.length; i++) {
      if (numsCopy[i] == nums[i]) {
        start++;
      } else {
        for (int j = nums.length - 1; j >= start; j--) {
          if (numsCopy[j] != nums[j]) {
            break;
          } else {
            end--;
          }
        }
        break;
      }
    }

    return end - start;
  }

  public static void main(String[] args) {
    int nums[] = {2, 6, 4, 8, 10, 9, 15};
    System.out.println(findUnsortedSubarray(nums));
  }
}
