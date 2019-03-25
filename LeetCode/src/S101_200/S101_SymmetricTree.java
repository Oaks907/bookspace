package S101_200;

import model.TreeNode;

/**
 * Create by haifei on 24/3/2019 9:02 PM.
 *
 * https://leetcode.com/problems/symmetric-tree/
 */
public class S101_SymmetricTree {

  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return false;
    }

    return helper(root.left, root.right);
  }

  public boolean helper(TreeNode left, TreeNode right) {
    if (left == null && right == null) {
      return true;
    }
    if (left == null || right == null) {
      return false;
    }

    if (left.val != right.val) {
      return false;
    }

    return helper(left.left, right.right) && helper(left.right, right.left);
  }
}
