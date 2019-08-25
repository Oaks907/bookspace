package S901_S1000;

import model.TreeNode;

/**
 * https://leetcode.com/problems/range-sum-of-bst/
 * Create by haifei on 25/8/2019 3:03 PM.
 */
public class S938_RangeSumofBST {

  int result = 0;

  public int rangeSumBST(TreeNode root, int L, int R) {
    rangeSum(root, L, R);
    return result;
  }

  private void rangeSum(TreeNode root, int left, int right) {
    if (null == root) {
      return;
    }

    if (root.val >= left && root.val <= right) {
      result += root.val;
      rangeSum(root.right, left, right);
      rangeSum(root.left, left, right);
      return;
    }

    if (root.val < left) {
      rangeSum(root.right, left, right);
    }
    if (root.val > right) {
      rangeSum(root.left, left, right);
    }
  }

  public static void main(String[] args) {

    TreeNode node10 = new TreeNode(10);
    TreeNode node5 = new TreeNode(5);
    TreeNode node15 = new TreeNode(15);
    TreeNode node3 = new TreeNode(3);
    TreeNode node7 = new TreeNode(7);
    TreeNode node18 = new TreeNode(19);

    node10.left = node5;
    node10.right = node15;

    node5.left = node3;
    node5.right = node7;

    node15.right = node18;

    final S938_RangeSumofBST rangeSumofBST = new S938_RangeSumofBST();
    System.out.println(rangeSumofBST.rangeSumBST(node10, 7, 15));
  }
}
