package S501_600;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by haifei on 25/1/2018.
 */
public class S532_KDiffPairsInAnArray {

  public static int findPairs(int[] nums, int k) {

    if (nums == null || nums.length == 0 || k < 0)   return 0;

    int result = 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i : nums) {
      map.put(i, map.getOrDefault(i, 0) + 1);
    }

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (k == 0) {
        if (entry.getValue() >= 2) {
          result++;
        }
      } else {
        if (map.containsKey(entry.getKey() + k)) {
          result++;
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int[] nums1 = {3, 1, 4, 1, 5};
    int[] nums2 = {1, 2, 3, 4, 5};
    int[] nums3 = {1, 3, 1, 5, 4};
    int[] nums4 = {1, 1, 1, 1, 1};
    System.out.println(findPairs(nums1, 2)); //2
    System.out.println(findPairs(nums2, 1)); //4
    System.out.println(findPairs(nums3, 0)); //1
    System.out.println(findPairs(nums4, 0)); //1
  }
}
