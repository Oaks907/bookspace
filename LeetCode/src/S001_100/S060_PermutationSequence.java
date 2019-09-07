package S001_100;

import org.junit.Assert;
import org.junit.Test;
import utils.PrintUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Create by haifei on 7/9/2019 11:22 AM.
 */
public class S060_PermutationSequence {

  // solution 1 time limit
  public String getPermutation(int n, int k) {

    int[] nums = new int[n];
    for (int i = 1; i <= n; i++) {
      nums[i - 1] = i;
    }

    recursionFill(nums, 0, n, k);
    Collections.sort(result);
    return result.get(k - 1);
  }

  int count = 0;
  List<String> result = new ArrayList<>();

  private void recursionFill(int[] nums, int start, int len, int k) {
    if (start >= len) {
      result.add(numberToString(nums));
      return;
    }

    for (int i = start; i < len; i++) {
      swap(nums, start, i);
      recursionFill(nums, start + 1, len, k);
      swap(nums, start, i);
    }
  }

  public void swap(int[] nums, int a, int b) {
    int temp = nums[a];
    nums[a] = nums[b];
    nums[b] = temp;
  }

  public String numberToString(int[] nums) {
    StringBuilder sb = new StringBuilder();
    for (int item : nums) {
      sb.append(item);
    }
    return sb.toString();
  }

  // solution 2
  public String getPermutation1(int n, int k) {
    ArrayList<Integer> nums = new ArrayList<Integer>();
    int remain = n;
    int[] fac = new int[n];
    for (int i = 0; i < n; i++) {
      if (i == 0 || i == 1) {
        fac[i] = 1;
      } else {
        fac[i] = fac[i - 1] * i;
      }
      nums.add(i + 1);
    }

    k--;
    StringBuilder res = new StringBuilder();

    while (k > 0) {
      int index = k / fac[remain - 1];
      res.append(nums.get(index));
      nums.remove(index);
      k = k % fac[remain - 1];
      remain--;
    }

    for (int i = 0; i < nums.size(); i++) {
      res.append(nums.get(i));
    }

    return res.toString();
  }

  @Test
  public void test() {
    S060_PermutationSequence permutationSequence = new S060_PermutationSequence();
    String result = permutationSequence.getPermutation(3, 3);
    Assert.assertEquals("213", result);
  }

  @Test
  public void test2() {
    S060_PermutationSequence permutationSequence = new S060_PermutationSequence();
    String result = permutationSequence.getPermutation(3, 5);
    Assert.assertEquals("312", result);
  }

  @Test
  public void test1() {
    S060_PermutationSequence permutationSequence = new S060_PermutationSequence();
    String result = permutationSequence.getPermutation1(4, 9);
    Assert.assertEquals("2314", result);
  }
}
