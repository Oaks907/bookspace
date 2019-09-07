package S001_100;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations/
 * Create by haifei on 4/9/2019 12:07 AM.
 */
public class S046_Permutations {

  // solution one
  public List<List<Integer>> permute_1(int[] nums) {

    int len = nums.length;
    if (len == 0) return res;

    // 采用前后元素交换的办法，dfs解题
    recursiveFill(nums, 0, len);

    return res;
  }

  List<List<Integer>> res = new ArrayList<List<Integer>>();

  private void recursiveFill(int[] nums, int start, int len) {
    if (start == len - 1) {
      List<Integer> list = new ArrayList<>();
      for (int j = 0; j < len; j++) {
        list.add(nums[j]);
      }
      res.add(list);
      return;
    }

    for (int i = start; i < len; i++) {
      swap(nums, start, i);
      recursiveFill(nums, start + 1, len);
      swap(nums, start, i);
    }
  }

  public void swap(int[] nums, int a, int b) {
    int temp = nums[a];
    nums[a] = nums[b];
    nums[b] = temp;
  }

  // solution two

  public List<List<Integer>> permute(int[] nums) {

    List<List<Integer>> result = new ArrayList<>();
    dfs(result, nums, new ArrayList<>(), new boolean[nums.length]);

    return result;
  }

  private void dfs(List<List<Integer>> list, int[] nums, List<Integer> temp, boolean[] isUsed) {
    if (temp.size() == nums.length) {
      list.add(new ArrayList<>(temp));
      return;
    }

    for (int i = 0; i < nums.length; i++) {
      if (!isUsed[i]) {
        temp.add(nums[i]);
        isUsed[i] = true;
        dfs(list, nums, temp, isUsed);
        temp.remove(temp.size() - 1);
        isUsed[i] = false;
      }
    }
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 3};

    S046_Permutations permutations = new S046_Permutations();
    System.out.println(permutations.permute_1(nums));
  }
}
