package S001_100;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets/
 * Create by haifei on 30/9/2019 8:18 AM.
 */
public class S078_Subsets {

  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    List<Integer> tempList = new ArrayList<>();
    backtrack(list, tempList, nums, 0);

    return list;
  }

  private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
    list.add(new ArrayList<>(tempList));
    for (int i = start; i < nums.length; i++) {
      tempList.add(nums[i]);
      backtrack(list, tempList, nums, i + 1);
      tempList.remove(tempList.size() - 1);
    }
  }

  @Test
  public void test() {
    int[] nums = {1, 2, 3};

    List<List<Integer>> result = subsets(nums);

    System.out.println(result);
  }
}
