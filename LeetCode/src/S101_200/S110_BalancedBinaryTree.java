package S101_200;

import model.TreeNode;

/**
 * Create by haifei on 24/3/2019 11:18 PM.
 *
 * https://leetcode.com/problems/balanced-binary-tree/
 */
public class S110_BalancedBinaryTree {

  boolean result = true;

  public boolean isBalanced(TreeNode root) {

    helper(root);
    return result;
  }

  public int helper(TreeNode node) {

    if (node == null) {
     return 0;
    }

    final int left = helper(node.left);
    final int right = helper(node.right);

    result = result && Math.abs(left - right) <= 1;

    return Math.max(left, right) + 1;
  }
}
