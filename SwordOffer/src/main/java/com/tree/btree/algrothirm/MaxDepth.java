package com.tree.btree.algrothirm;

import com.haifei.model.TreeNode;

/**
 * Create by haifei on 11/12/2018 9:01 AM.
 * 二叉树的最大深度
 * 二叉树的最小深度
 */
public class MaxDepth {
  /**
   * 二叉树的最大深度
   *
   * @param root
   * @return
   */
  public int maxDepth(TreeNode root) {
    if (null == root) {
      return 0;
    }
    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
  }

  /**
   * 二叉树的最小深度
   *
   * @param root
   * @return
   */
  public int minDepth(TreeNode root) {
    if (null == root) {
      return 0;
    }
    return getMin(root);
  }

  private int getMin(TreeNode root) {
    if (null == root) {
      return Integer.MAX_VALUE;
    }
    if (root.left == null && root.right == null) {
      return 1;
    }

    return Math.min(getMin(root.left), getMin(root.right)) + 1;
  }

  public static void main(String[] args) {
    final MaxDepth maxDepth = new MaxDepth();

    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    node1.left = node2;
    node1.right = node3;
    node2.left = node4;
    node2.right = node5;
    node3.left = node6;

    System.out.println("最大深度" + maxDepth.maxDepth(node1));
    System.out.println("最小深度" + maxDepth.minDepth(node1));
  }

}
