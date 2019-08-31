package S501_600;

import model.TreeNode;
import org.junit.Before;
import org.junit.Test;
import utils.PrintUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/most-frequent-subtree-sum/
 * Create by haifei on 31/8/2019 12:23 PM.
 */
public class S508_MostFrequentSubtreeSum {

  Map<Integer, Integer> map = new HashMap<>();

  public int[] findFrequentTreeSum(TreeNode root) {

    int mostFrequentValue = Integer.MIN_VALUE;
    List<Integer> result = new ArrayList<>();
    helper(root);
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (entry.getValue() > mostFrequentValue) {
        mostFrequentValue = entry.getValue();
        result.clear();
        result.add(entry.getKey());
      } else if (entry.getValue() == mostFrequentValue) {
        result.add(entry.getKey());
      }
    }
    int[] arr = new int[result.size()];
    for (int i = 0; i < result.size(); i++) {
      arr[i] = result.get(i);
    }
    return arr;
  }

  private int helper(TreeNode node) {
    if (null == node) {
      return 0;
    }

    int left = helper(node.left);
    int right = helper(node.right);

    int sum = left + right + node.val;
    map.put(sum, map.getOrDefault(sum, 0) + 1);
    return sum;
  }

  TreeNode node1;
  TreeNode node2;
  TreeNode node3;
  TreeNode node4;
  TreeNode node5;
  TreeNode node6;
  TreeNode node7;
  TreeNode node8;

  @Before
  public void before() {
    node1 = new TreeNode(1);
    node2 = new TreeNode(2);
    node3 = new TreeNode(3);
    node4 = new TreeNode(4);
    node5 = new TreeNode(5);
    node6 = new TreeNode(6);
    node7 = new TreeNode(7);
    node8 = new TreeNode(8);
  }

  @Test
  public void test() {
    node3 = new TreeNode(-3);

    node5.left = node2;
    node5.right = node3;

    S508_MostFrequentSubtreeSum frequentSubtreeSum = new S508_MostFrequentSubtreeSum();

    PrintUtils.printArray(frequentSubtreeSum.findFrequentTreeSum(node5));
  }

  @Test
  public void test2() {
    node5.left = node2;
    node5.right = new TreeNode(-5);

    S508_MostFrequentSubtreeSum frequentSubtreeSum = new S508_MostFrequentSubtreeSum();
    PrintUtils.printArray(frequentSubtreeSum.findFrequentTreeSum(node5));
  }

}
