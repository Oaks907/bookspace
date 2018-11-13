package com.haifei;

import com.haifei.model.TreeNode;

/**
 * Create by haifei on 13/11/2018 11:43 PM.
 */
public class S39_2_balancedBinaryTree {

  /**
   * 解法一：会多次重复遍历一些节点的深度
   */
  public boolean isBalanced(TreeNode node) {
    if (null == node) {
      return true;
    }
    int leftDepth = treeDepth(node.left);
    int rightDepth = treeDepth(node.right);

    int diff = Math.abs(leftDepth - rightDepth);
    if (diff > 1) {
      return false;
    }
    return isBalanced(node.left) && isBalanced(node.right);
  }

  private int treeDepth(TreeNode node) {
    if (null == node) {
      return 0;
    }
    int leftDepth = treeDepth(node.left);
    int rightDepth = treeDepth(node.right);

    return leftDepth > rightDepth ? (leftDepth + 1) : (rightDepth + 1);
  }

  /**
   * 解法二：节点只遍历一次
   */
  public boolean isBalanced2(TreeNode node, int depth) {
    if (null == node) {
      depth = 0;
      return true;
    }

    int left = 0, right = 0;
    if (isBalanced2(node.left, left) && isBalanced2(node.right, right)) {
      int diff = Math.abs(left - right);
      if (diff < 1) {
        depth = left > right ? left + 1 : right + 1;
        return true;
      }
    }
    return false;
  }
}
