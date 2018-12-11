package S001_100;

import jdk.nashorn.internal.ir.ReturnNode;
import model.TreeNode;

/**
 * Create by haifei on 10/12/2018 11:41 PM.
 * https://leetcode.com/problems/validate-binary-search-tree/
 */
public class S98_isValidBST {
  public boolean isValidBST(TreeNode root) {

    return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
    if (root == null) {
      return true;
    }
    if (root.val >= maxVal || root.val <= minVal) {
      return false;
    }

    return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
  }
}
