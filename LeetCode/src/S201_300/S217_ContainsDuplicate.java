package S201_300;

import java.util.HashSet;

/**
 * Create by haifei on 17/1/2018.
 */
public class S217_ContainsDuplicate {
  public static boolean containsDuplicate(int[] nums) {
    if (nums == null || nums.length == 0) {
      return false;
    }

    HashSet<Integer> map = new HashSet<Integer>();
    for (int i : nums) {
      if (!map.add(i)) {
        return true;
      }
    }
    for (int i : nums) {
      if (map.contains(i)) {
        return true;
      }
      map.add(i);
    }
    return false;
  }

  public static void main(String[] args) {
    int nums[] = {0};  //false
    int nums1[] = {0, 0};  //true
    System.out.println(containsDuplicate(nums));
    System.out.println(containsDuplicate(nums1));
  }
}
