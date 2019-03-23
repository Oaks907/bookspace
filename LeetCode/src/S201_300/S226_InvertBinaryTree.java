package S201_300;

import model.TreeNode;

/**
 * Create by haifei on 23/3/2019 6:32 PM.
 *
 * https://leetcode.com/problems/invert-binary-tree/
 *
 */
public class S226_InvertBinaryTree {

  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return root;
    }
    TreeNode tmp = root.left;
    root.left = root.right;
    root.right = tmp;
    invertTree(root.left);
    invertTree(root.right);

    return root;
  }
}
