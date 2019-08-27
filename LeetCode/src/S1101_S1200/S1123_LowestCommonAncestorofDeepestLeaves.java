package S1101_S1200;

import com.sun.org.apache.regexp.internal.REUtil;
import model.TreeNode;

/**
 * Create by haifei on 27/8/2019 10:54 PM.
 */
public class S1123_LowestCommonAncestorofDeepestLeaves {

  public TreeNode lcaDeepestLeaves(TreeNode root) {

    helper(root, 0);

    return result;
  }

  TreeNode result = null;
  int maxDepth = -1;

  private int helper(TreeNode root, int depth) {
    if (null == root) {
      return depth - 1;
    }

    final int left = helper(root.left, depth + 1);
    final int right = helper(root.right, depth + 1);

    if (depth > maxDepth) {
      maxDepth = depth;
    }

    if (left == maxDepth && right == maxDepth) {
      result = root;
    }

    return Math.max(left, right);
  }


}
