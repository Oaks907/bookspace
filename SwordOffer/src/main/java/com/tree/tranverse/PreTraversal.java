package com.tree.tranverse;

import com.haifei.model.TreeNode;

import java.util.Stack;

/**
 * Create by haifei on 24/12/2018 3:18 PM.
 */
public class PreTraversal {

  private void preTraversal(TreeNode node) {
    Stack<TreeNode> stack = new Stack<>();

    while (node != null || !stack.isEmpty()) {
      while (node != null) {
        System.out.print(node.val + " ");
        stack.push(node);
        node = node.left;
      }

      if (!stack.isEmpty()) {
        final TreeNode item = stack.pop();
        node = item.right;
      }
    }
  }
  private void middleTraversal(TreeNode node) {
    Stack<TreeNode> stack = new Stack<>();

    while (node != null || !stack.isEmpty()) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }

      if (!stack.isEmpty()) {
        final TreeNode item = stack.pop();
        System.out.print(item.val + " ");

        node = item.right;
      }
    }
  }

  private void lastTraversal(TreeNode node) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode lastVisit = node;

    while (node != null || !stack.isEmpty()) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }

      //如果其右子树也为空，或者右子树已经访问
      //则可以直接输出当前节点的值
      if (node.right == null || node.right == lastVisit) {
        System.out.print(node.val + " ");
        stack.pop();
        lastVisit = node;
        node = null;
      } else {
        //否则，继续遍历右子树
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

    final PreTraversal traversal = new PreTraversal();
    System.out.println("前序遍历：");
    traversal.preTraversal(node1);

    System.out.println();
    traversal.middleTraversal(node1);

  }
}
