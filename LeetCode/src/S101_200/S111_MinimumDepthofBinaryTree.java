package S101_200;

import jdk.nashorn.internal.ir.IfNode;
import model.TreeNode;

/**
 * Create by haifei on 14/1/2019 12:25 AM.
 */
public class S111_MinimumDepthofBinaryTree {

  public int minDepth(TreeNode root) {
    if (null == root) {
      return 0;
    }

    int left = minDepth(root.left);
    int right = minDepth(root.right);

    if (left == 0 || right == 0) {
      return left + right + 1;
    } else {
      return Math.min(left, right) + 1;
    }
  }

  public static void main(String[] args) {

    TreeNode node3 = new TreeNode(3);
    TreeNode node7 = new TreeNode(7);
    TreeNode node9 = new TreeNode(9);
    TreeNode node15 = new TreeNode(15);
    TreeNode node20 = new TreeNode(20);

    node3.left = node9;
    node3.right = node20;

    node20.left = node15;
    node20.right = node7;

    final S111_MinimumDepthofBinaryTree binaryTree =
      new S111_MinimumDepthofBinaryTree();

    System.out.println(binaryTree.minDepth(node3));
  }
}
