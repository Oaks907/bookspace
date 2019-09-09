package S701_800;

import model.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 * <p>
 * Create by haifei on 8/9/2019 3:23 PM.
 */
public class S783_MinimumDistanceBetweenBSTNodes {

  public int minDiffInBST(TreeNode root) {

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
    if (pre != Integer.MAX_VALUE) {
      minResult = Math.min(minResult, node.val - pre);
    } else {
      if (node.left != null) {
        minResult = Math.min(minResult, node.val - node.left.val);
      }
      if (node.right != null) {
        minResult = Math.min(minResult, node.right.val - node.val);
      }
    }

    pre = node.val;
    helper(node.right);
  }

  @Test
  public void test() {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    TreeNode node7 = new TreeNode(7);
    TreeNode node8 = new TreeNode(8);

    node4.left = node2;
    node4.right = node6;

    node2.left = node1;
    node2.right = node3;

    S783_MinimumDistanceBetweenBSTNodes bstNodes =
      new S783_MinimumDistanceBetweenBSTNodes();

    int result = bstNodes.minDiffInBST(node4);

    Assert.assertEquals(1, result);
  }
}
