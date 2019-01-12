package com.tree.btree.algrothirm;

import com.haifei.model.TreeNode;

import java.util.Stack;

/**
 * Create by haifei on 9/1/2019 9:17 PM.
 * 输入一个二叉树和一个整数，打印出二叉树中节点值的和等于输入整数所有的路径
 */
public class FindPath {

  public void findPath(TreeNode root, int targetValue) {
    if (null == root) {
      return;
    }

    Stack<TreeNode> stack = new Stack<>();
    findPath(root, stack, 0, targetValue);
  }

  private void findPath(TreeNode root, Stack<TreeNode> stack, int currentValue, int targetValue) {
    currentValue += root.val;
    stack.add(root);

    if (root.left == null && null == root.right) {
      if (currentValue == targetValue) {
        for (TreeNode node : stack) {
          System.out.print(node.val + " ");
        }
      }
    }

    if (root.left != null) {
      findPath(root.left, stack, currentValue, targetValue);
    }

    if (root.right != null) {
      findPath(root.right, stack, currentValue, targetValue);
    }

    stack.pop();
  }
}
