package com.tree.btree.algrothirm;

import com.haifei.model.TreeNode;

/**
 * Create by haifei on 7/1/2019 3:19 PM.
 */
public class IsBalanced {

  public boolean isBalanced(TreeNode node) {
    return getDepth(node) != -1;
  }

  private int getDepth(TreeNode node) {

    if (node == null) {
      return 0;
    }

    int left = getDepth(node.left);
    int right = getDepth(node.right);

    if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
      return -1;
    }

    return Math.max(left, right) + 1;
  }

  public static void main(String[] args) {

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

    final IsBalanced isBalanced = new IsBalanced();
    System.out.println(isBalanced.isBalanced(node1));
  }
}
