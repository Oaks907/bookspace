package S001_100;

import model.TreeNode;

/**
 * Create by haifei on 23/3/2019 11:15 PM.
 *
 * https://leetcode.com/problems/same-tree/
 */
public class S100_SameTree {

  public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) {
      return true;
    }

    if (p == null || q == null) {
      return false;
    }

    if (p.val != q.val) {
      return false;
    }

    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
  }
}
