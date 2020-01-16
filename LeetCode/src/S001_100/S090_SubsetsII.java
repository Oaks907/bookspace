package S001_100;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create by haifei on 14/1/2020 11:27 PM.
 */
public class S090_SubsetsII {

  public List<List<Integer>> subsetsWithDup(int[] nums) {

    Arrays.sort(nums);

    List<List<Integer>> result = new ArrayList<>();

    helper(nums, 0, result, new ArrayList<>());

    return result;
  }

  public void helper(int[] nums, int index, List<List<Integer>> result, List<Integer> list) {
    if (index > nums.length) {
      return;
    }

    result.add(new ArrayList<>(list));

    for (int i = index; i < nums.length; i++) {
      if (i != 0 && i != index && nums[i - 1] == nums[i]) {
        continue;
      }
      list.add(nums[i]);
      helper(nums, i + 1, result, new ArrayList<>(list));
      list.remove(list.size() - 1);
    }
  }


  @Test
  public void test() {
    int[] arr = {1, 2, 2};

    List<List<Integer>> result = subsetsWithDup(arr);

    System.out.println(result);
  }
}
