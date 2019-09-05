package S101_200;

import model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/path-sum-ii/
 * Create by haifei on 5/9/2019 7:51 AM.
 */
public class S113_PathSumII {

  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    helper(root, new ArrayList<>(), sum);
    return result;
  }

  List<List<Integer>> result = new ArrayList<>();

  private void helper(TreeNode node, List<Integer> list, int sum) {

    if (node == null) {
      return;
    }

    list.add(node.val);
    sum -= node.val;

    if (node.left == null && node.right == null && sum == 0) {
      result.add(new ArrayList<>(list));
    } else {
      helper(node.left, list, sum);
      helper(node.right, list, sum);
    }
    list.remove(list.size() - 1);
  }

  @Test
  public void test() {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);

    node1.left = node2;
    node1.right = node3;
    node2.left = node4;
    node2.right = node5;

    S113_PathSumII pathSumII = new S113_PathSumII();

    System.out.println(pathSumII.pathSum(node1, 4));
  }
}
