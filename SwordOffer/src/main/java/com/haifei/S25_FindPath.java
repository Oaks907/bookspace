package com.haifei;

import com.haifei.model.TreeNode;

import java.util.Stack;

/**
 * Create by haifei on 16/7/2018.
 */
public class S25_FindPath {

  private Stack<Integer> stack = new Stack<>();

  private void findPathRecursion(TreeNode node, int value) {
    if (node == null) {
      return;
    }

    stack.push(node.val);
    value = value - node.val;

    boolean isLeaf = (node.left == null) && (node.right == null);
    if (isLeaf && value == 0) {
      for (Integer val : stack) {
        System.out.print(val + " ");
      }
      System.out.println();
      //pop当前node的值
      stack.pop();
      return;
    }

    if (node.left != null) {
      findPathRecursion(node.left, value);
    }

    if (node.right != null) {
      findPathRecursion(node.right, value);
    }

    stack.pop();
  }

  public static void main(String[] args) {
    TreeNode node10 =  new TreeNode(10);
    TreeNode node12 = new TreeNode(12);
    TreeNode node5 = new TreeNode(5);
    TreeNode node4 = new TreeNode(4);
    TreeNode node7 = new TreeNode(7);

    node10.left = node5;
    node10.right = node12;
    node5.left = node4;
    node5.right = node7;

    new S25_FindPath().findPathRecursion(node10, 22);
  }
}
