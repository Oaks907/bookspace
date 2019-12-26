package S001_100;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-ii/
 * Create by haifei on 26/12/2019 8:56 AM.
 */
public class S040_CombinationSumII {

  List<List<Integer>> result = new ArrayList<>();
  List<Integer> list = new ArrayList<>();

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {

    Arrays.sort(candidates);
    helper(candidates, 0, target);

    return result;
  }

  private void helper(int[] candidates, int index, int target) {

    if (target == 0) {
      result.add(new ArrayList<>(list));
      return;
    }

    if (target < 0) {
      return;
    }

    for (int i = index; i < candidates.length; i++) {
      if (i > index && candidates[i] == candidates[i - 1]) {
        continue;
      }

      list.add(list.size(), candidates[i]);
      helper(candidates, i + 1, target - candidates[i]);
      list.remove(list.size() - 1);
    }
  }

  @Test
  public void test1() {

    int[] arr = {10, 1, 2, 7, 6, 1, 5};

    // [
    //  [1, 7],
    //  [1, 2, 5],
    //  [2, 6],
    //  [1, 1, 6]
    //]
    System.out.println(combinationSum2(arr, 8));
  }

  @Test
  public void test2() {
    int[] arr = {2, 5, 2, 1, 2};

    // [
    //  [1,2,2],
    //  [5]
    //]
    System.out.println(combinationSum2(arr, 5));
  }

  @Test
  public void test3() {
    int[] arr = {14, 6, 25, 9, 30, 20, 33, 34, 28, 30, 16, 12, 31, 9, 9, 12, 34, 16, 25, 32, 8, 7
      , 30, 12, 33, 20, 21, 29, 24, 17, 27, 34, 11, 17, 30, 6, 32, 21, 27, 17, 16, 8, 24, 12, 12,
      28, 11, 33, 10, 32, 22, 13, 34, 18, 12};

    // [
    //  [1,2,2],
    //  [5]
    //]
    System.out.println(combinationSum2(arr, 27));
  }


}
