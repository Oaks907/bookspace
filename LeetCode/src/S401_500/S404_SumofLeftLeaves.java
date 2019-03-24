package S401_500;

import model.TreeNode;
import org.junit.Test;

/**
 * Create by haifei on 23/3/2019 11:22 PM.
 * <p>
 * Find the sum of all left leaves in a given binary tree.
 * https://leetcode.com/problems/sum-of-left-leaves/
 */
public class S404_SumofLeftLeaves {

  public int sumOfLeftLeaves(TreeNode root) {
    if (root == null) {
      return 0;
    }

    return recursion(root, true);
  }

  private int recursion(TreeNode root, boolean isLeft) {
    if (root == null) {
      return 0;
    }

    if (isLeft && root.left == null && root.right == null) {
      return root.val;
    }

    final int left = recursion(root.left, true);
    final int right = recursion(root.right, false);

    return left + right;
  }

  @Test
  public void test() {
    final S404_SumofLeftLeaves leftLeaves = new S404_SumofLeftLeaves();

    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    TreeNode node7 = new TreeNode(7);

    node4.left = node2;
    node4.right = node6;

    node2.left = node1;
    node2.right = node3;

    node6.left = node5;
    node6.right = node7;

    System.out.println(leftLeaves.sumOfLeftLeaves(node4));
  }


}
