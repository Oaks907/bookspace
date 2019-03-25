package S501_600;

import model.TreeNode;
import org.junit.Test;

/**
 * Create by haifei on 24/3/2019 8:09 PM.
 */
public class S543_DiameterofBinaryTree {

  int result = 0;

  public int diameterOfBinaryTree(TreeNode root) {
    helper(root);
    return result;
  }

  private int helper(TreeNode node) {
    if (node == null) {
      return 0;
    }

    final int left = helper(node.left);
    final int right = helper(node.right);

    result = Math.max(result, left + right);
    return Math.max(left, right) + 1;
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

    final S543_DiameterofBinaryTree binaryTree = new S543_DiameterofBinaryTree();
    System.out.println(binaryTree.diameterOfBinaryTree(node1));
  }
}
