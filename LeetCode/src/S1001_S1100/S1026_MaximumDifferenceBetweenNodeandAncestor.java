package S1001_S1100;

import model.TreeNode;

/**
 * Create by haifei on 29/8/2019 11:21 PM.
 */
public class S1026_MaximumDifferenceBetweenNodeandAncestor {

  public int maxAncestorDiff(TreeNode root) {
    return helper(root, root.val, root.val);
  }

  private int helper(TreeNode node, int maxAncestor, int minAncestor) {

    if (null == node) {
      return 0;
    }

    int val = Math.max(Math.abs(maxAncestor - node.val), Math.abs(minAncestor - node.val));

    if (node.val > maxAncestor) {
      maxAncestor = node.val;
    }
    if (node.val < minAncestor) {
      minAncestor = node.val;
    }

    int left = helper(node.left, maxAncestor, minAncestor);
    int right = helper(node.right, maxAncestor, minAncestor);

    return Math.max(Math.max(left, right), val);
  }

  public static void main(String[] args) {
    TreeNode node0 = new TreeNode(0);
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node6 = new TreeNode(6);
    TreeNode node7 = new TreeNode(7);
    TreeNode node8 = new TreeNode(8);
    TreeNode node10 = new TreeNode(10);
    TreeNode node14 = new TreeNode(14);

    // 示例 1
//    node8.left = node3;
//    node8.right = node10;
//
//    node3.left = node1;
//    node3.right = node6;
//
//    node10.right = node14;
    // 示例 2
    node1.right = node2;
    node2.right = node0;
    node0.right = node3;

    S1026_MaximumDifferenceBetweenNodeandAncestor ancestor =
      new S1026_MaximumDifferenceBetweenNodeandAncestor();
    System.out.println(ancestor.maxAncestorDiff(node1));
  }
}
