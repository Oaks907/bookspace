package S501_600;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * https://leetcode.com/problems/array-nesting/
 * Create by haifei on 3/10/2019 6:45 AM.
 */
public class S565_ArrayNesting {


  /**
   * Time
   *
   * @param nums
   * @return
   */
  public int arrayNesting(int[] nums) {

    if (null == nums || 0 == nums.length) {
      return 0;
    }

    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      map.put(i, nums[i]);
    }

    int maxCount = Integer.MIN_VALUE;

    for (int i = 0; i < nums.length; i++) {
      Set<Integer> set = new HashSet<>();
      set.add(i);
      int next = nums[i];
      while (true) {
        int value = nums[next];
        set.add(next);
        if (!set.contains(value)) {
          next = value;
        } else {
          break;
        }
      }
      if (set.size() > maxCount) {
        maxCount = set.size();
      }
    }

    return maxCount;
  }

  public int arrayNesting_1(int[] nums) {
    int ans = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      ans = Math.max(ans, reverse(nums, i));
    }
    return ans;
  }

  private int reverse(int[] nums, int i) {
    int cnt = 0;
    int value = i;

    while (nums[value] != -1) {
      cnt++;
      int temp = nums[value];
      nums[value] = -1;
      value = temp;
    }

    return cnt;
  }

  @Test
  public void test() {
    int[] arr = {5, 4, 0, 3, 1, 6, 2};

    System.out.println(arrayNesting_1(arr));
  }
}
