package S201_300;

import org.junit.Assert;
import org.junit.Test;
import utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 8/1/2020 10:24 AM.
 */
public class S216_CombinationSumIII {

  List<List<Integer>> result = new ArrayList<>();
  List<Integer> list = new ArrayList<>();

  public List<List<Integer>> combinationSum3(int k, int n) {

    int[] arr = new int[9];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = i + 1;
    }

    helper(arr, 0, k, n);

    return result;
  }

  public void helper(int[] arr, int curIndex, int k, int val) {
    if (curIndex > arr.length) {
      return;
    }
    if (val < 0) {
      return;
    }

    if (val == 0 && k == 0) {
      result.add(new ArrayList<>(list));
      return;
    }
    if (k <= 0) {
      return;
    }

    for (int i = curIndex; i < arr.length; i++) {
      list.add(arr[i]);
      helper(arr, i + 1, k - 1, val - arr[i]);
      list.remove(list.size() - 1);
    }
  }

  @Test
  public void test3() {

    List<List<Integer>> result = combinationSum3(3, 15);

    System.out.println(result);

    Assert.assertEquals(8, result.size());
  }

  @Test
  public void test() {

    List<List<Integer>> result = combinationSum3(3, 7);

    System.out.println(result);
  }

  @Test
  public void test1() {

    List<List<Integer>> result = combinationSum3(3, 9);

    System.out.println(result);
  }


}
