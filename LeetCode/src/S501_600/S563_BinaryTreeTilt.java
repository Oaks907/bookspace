package S501_600;

import model.TreeNode;
import org.junit.Test;

/**
 * Create by haifei on 24/3/2019 9:19 AM.
 *
 * https://leetcode.com/problems/binary-tree-tilt/
 *
 * Given a binary tree, return the tilt of the whole tree.
 *
 * The tilt of a tree node is defined as the absolute difference between the sum of all left
 * subtree node values and the sum of all right subtree node values. Null node has tilt 0.
 *
 * The tilt of the whole tree is defined as the sum of all nodes' tilt.
 */
public class S563_BinaryTreeTilt {

  int result = 0;

  public int findTilt(TreeNode root) {

    if (root == null) {
      return 0;
    }

    recursion(root);

    return result;
  }

  private int recursion(TreeNode root) {

    if (root == null) {
      return 0;
    }

    final int left = recursion(root.left);
    final int right = recursion(root.right);

    result += Math.abs(left - right);

    return left + right + root.val;
  }

  @Test
  public void test() {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);

    node1.left = node2;
    node1.right = node3;

    final S563_BinaryTreeTilt treeTilt = new S563_BinaryTreeTilt();
    System.out.println(treeTilt.findTilt(node1));
  }
}
