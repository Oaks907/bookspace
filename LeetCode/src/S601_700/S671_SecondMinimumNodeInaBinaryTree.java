package S601_700;

import model.TreeNode;
import org.junit.Test;

/**
 * Create by haifei on 24/3/2019 8:50 PM.
 * <p>
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
 */
public class S671_SecondMinimumNodeInaBinaryTree {

  int smallest = -1;
  int secondmini = Integer.MAX_VALUE;

  public int findSecondMinimumValue(TreeNode root) {

    if (root == null) {
      return -1;
    }

    smallest = root.val;

    helper(root);

    return secondmini == Integer.MAX_VALUE ? -1 : secondmini;
  }

  private void helper(TreeNode node) {
    if (null == node) {
      return;
    }
    if (node.val > smallest && node.val < secondmini) {
      secondmini = node.val;
    }

    helper(node.left);
    helper(node.right);
  }

  @Test
  public void test() {

    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);

    node1.left = node2;
    node1.right = node3;

    node2.left = node4;
    node2.right = node5;

    final S671_SecondMinimumNodeInaBinaryTree binaryTree =
      new S671_SecondMinimumNodeInaBinaryTree();
    System.out.println(binaryTree.findSecondMinimumValue(node1));
  }
}
