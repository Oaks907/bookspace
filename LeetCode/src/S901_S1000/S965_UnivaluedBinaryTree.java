package S901_S1000;

import model.TreeNode;

/**
 * Create by haifei on 16/1/2019 9:04 PM.
 * 判断树的值都是相同的
 * https://leetcode.com/problems/univalued-binary-tree/
 */
public class S965_UnivaluedBinaryTree {

  public boolean isUnivalTree_recursion(TreeNode root) {
    if (root == null) {
      return true;
    }

    if (root.left != null && root.left.val != root.val) {
      return false;
    }

    if (root.right != null && root.right.val != root.val) {
      return false;
    }

    boolean left = isUnivalTree_recursion(root.left);
    boolean right = isUnivalTree_recursion(root.right);

    return left && right;
  }

}
