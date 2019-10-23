package S001_100;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/
 * Create by haifei on 22/10/2019 11:18 PM.
 */
public class S039_CombinationSum {

  List<List<Integer>> result = new ArrayList<>();
  List<Integer> list = new ArrayList<>();

  public List<List<Integer>> combinationSum(int[] candidates, int target) {

    Arrays.sort(candidates);
    combinationSum(candidates, 0, target);
    return result;
  }

  private void combinationSum(int[] candidates, int index, int target) {
    if (index == candidates.length) {
      return;
    }
    if (target == 0) {
      result.add(new ArrayList<>(list));
      return;
    }

    for (int i = index; i < candidates.length; i++) {
      if (target >= candidates[i]) {
        list.add(candidates[i]);
        combinationSum(candidates, i, target - candidates[i]);
        list.remove(list.size() - 1);
      }
    }
  }

  @Test
  public void test() {

    int[] arr = {2, 3, 6, 7};
    List<List<Integer>> result = combinationSum(arr, 7);

    System.out.println(result);
    Assert.assertEquals(2, result.size());
  }

  @Test
  public void test1() {

    int[] arr = {2, 3, 5};
    List<List<Integer>> result = combinationSum(arr, 8);

    System.out.println(result);
    Assert.assertEquals(3, result.size());
  }
}
