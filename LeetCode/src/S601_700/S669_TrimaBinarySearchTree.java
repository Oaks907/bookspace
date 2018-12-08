package S601_700;

import model.TreeNode;

/**
 * Create by haifei on 7/12/2018 8:45 PM.
 */
public class S669_TrimaBinarySearchTree {

  public TreeNode trimBST(TreeNode root, int L, int R) {
    return trim(root, L, R);
  }

  public TreeNode trim(TreeNode root, int L, int R) {
    if (root == null) {
      return null;
    }
    if (root.val < L) {
      return trim(root.right, L, R);
    }

    if (root.val > R) {
      return trim(root.left, L, R);
    }

    root.left = trim(root.left, L, R);
    root.right = trim(root.right, L, R);
    return root;
  }

  // 遍历方式
  public TreeNode trimBST2(TreeNode root, int L, int R) {
    if (null == root) {
      return null;
    }
    //定位root的位置
    while (root.val < L || root.val > R) {
      if (root.val < L) {
        root = root.right;
      }
      if (root.val > R) {
        root = root.left;
      }
    }

    TreeNode node = root;
    //删除左子树无用的节点
    while (null != node) {
      while (node.left != null && node.left.val < L) {
        node.left = node.left.right;
      }
      node = node.left;
    }

    node = root;
    while (null != node) {
      while (node.right != null && node.right.val > R) {
        node.right = node.right.left;
      }
      node = node.right;
    }
    return root;
  }
}
