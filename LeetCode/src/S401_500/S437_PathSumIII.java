package S401_500;

import model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static utils.PrintUtils.buildTree;

/**
 * Create by haifei on 26/3/2019 11:31 AM.
 * <p>
 * https://leetcode.com/problems/path-sum-iii/
 */
public class S437_PathSumIII {

  int count = 0;

  public int pathSum(TreeNode root, int sum) {
    dfs(root, sum);
    return count;
  }

  private void dfs(TreeNode root, int sum) {
    if (root == null) {
      return;
    }

    helper(root, sum);

    dfs(root.left, sum);
    dfs(root.right, sum);
  }

  private void helper(TreeNode root, int sum) {

    if (root == null) {
      return;
    }

    sum -= root.val;
    if (sum == 0) {
      count++;
    }

    helper(root.left, sum);
    helper(root.right, sum);
  }

  @Test
  public void test1() {

    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);

    node1.right = node2;
    node2.right = node3;
    node3.right = node4;
    node4.right = node5;

    final S437_PathSumIII pathSumIII = new S437_PathSumIII();
    System.out.println(pathSumIII.pathSum(node1, 3));
  }

  @Test
  public void test() {

    Integer[] ints = {10, 5, -3, 3, 2, null, 11, 3, -2, null, 1};
    final TreeNode node = buildTree(ints);

    final S437_PathSumIII pathSumIII = new S437_PathSumIII();
    System.out.println(pathSumIII.pathSum(node, 8));
  }
}
