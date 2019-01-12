package com.tree.btree.algrothirm.traverse;

import com.haifei.model.TreeNode;

import java.util.Stack;

/**
 * Create by haifei on 8/1/2019 1:36 PM.
 */
public class ReverseTraverse {

  public void reverseTraverse(TreeNode node) {

    Stack<TreeNode> stack = new Stack<>();
    TreeNode lastViewNode = null;
    while (node != null || !stack.isEmpty()) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }

      node = stack.peek();
      if (node.right == null || node.right == lastViewNode) {
        System.out.print(node.val + " ");
        node = stack.pop();
        lastViewNode = node;
        node = null;
      } else {
        node = node.right;
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

    final ReverseTraverse reverseTraverse = new ReverseTraverse();
    System.out.println("递归方式:");
//    reverseTraverse.perTraverse_recursion(node1);
    System.out.println();
    System.out.println("非递归方式_1:");
    reverseTraverse.reverseTraverse(node1);
    System.out.println();
    System.out.println("非递归方式_2:");
//    preTraverse.preTraverse_Two(node1);
  }
}
