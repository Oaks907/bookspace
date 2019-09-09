package S501_600;

import model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 8/9/2019 3:05 PM.
 */
public class S530_MinimumAbsoluteDifferenceinBST {


  public int getMinimumDifference(TreeNode root) {

    helper(root);

    return minResult;
  }

  private int minResult = Integer.MAX_VALUE;
  private int pre = Integer.MAX_VALUE;

  private void helper(TreeNode node) {
    if (null == node) {
      return;
    }

    helper(node.left);
    minResult = Math.min(minResult, Math.abs(pre - node.val));
    pre = node.val;
    helper(node.right);
  }
}
