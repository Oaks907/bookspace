package com.tree.btree.algrothirm.traverse;

import com.haifei.model.TreeNode;

import java.util.Stack;

/**
 * Create by haifei on 7/1/2019 3:47 PM.
 * 前序遍历
 */
public class PreTraverse {

  /**
   * 递归方法遍历
   *
   * @param node
   */
  public void perTraverse_recursion(TreeNode node) {
    if (node == null) {
      return;
    }
    System.out.print(node.val + " ");
    perTraverse_recursion(node.left);
    perTraverse_recursion(node.right);
  }

  /**
   * 非递归遍历,前序遍历使用
   *
   * @param node
   */
  public void preTraverse_One(TreeNode node) {

    Stack<TreeNode> stack = new Stack<>();
    while (node != null || !stack.isEmpty()) {
      while (node != null) {
        System.out.print(node.val + " ");
        stack.push(node);
        node = node.left;
      }
      if (!stack.isEmpty()) {
        node = stack.pop();
        node = node.right;
      }
    }
  }

  public void preTraverse_Two(TreeNode node) {

    Stack<TreeNode> stack = new Stack<>();

    while (node != null || !stack.isEmpty()) {
      if (node != null) {
        System.out.print(node.val + " ");
        stack.push(node);
        node = node.left;
      } else {
        final TreeNode item = stack.pop();
        node = item.right;
      }
    }
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

    final PreTraverse preTraverse = new PreTraverse();
    System.out.println("递归方式:");
    preTraverse.perTraverse_recursion(node1);
    System.out.println();
    System.out.println("非递归方式_1:");
    preTraverse.preTraverse_One(node1);
    System.out.println();
    System.out.println("非递归方式_2:");
    preTraverse.preTraverse_Two(node1);
  }
}
