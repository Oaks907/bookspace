package com.haifei;

import com.haifei.model.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Create by haifei on 15/7/2018.
 */
public class S23_printFromTopToBottom {

  private void printFromTopToBottom(TreeNode head) {
    if (head == null) {
      return;
    }
    Queue<TreeNode> nodeQueue = new ArrayDeque<TreeNode>();

    nodeQueue.add(head);

    while (!nodeQueue.isEmpty()) {

      TreeNode node = nodeQueue.remove();
      if (node.left != null) {
        nodeQueue.add(node.left);
      }
      if (node.right!= null) {
        nodeQueue.add(node.right);
      }
      System.out.print(node.val + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    TreeNode head = new TreeNode(8);
    TreeNode val6 = new TreeNode(6);
    TreeNode val5 = new TreeNode(5);
    TreeNode val7 = new TreeNode(7);
    TreeNode val10 = new TreeNode(10);
    TreeNode val9 = new TreeNode(9);
    TreeNode val11 = new TreeNode(11);

    head.left = val6;
    head.right = val10;
    val6.left = val5;
    val6.right = val7;
    val10.left = val9;
    val10.right = val11;

    S23_printFromTopToBottom printFromTopToBottom = new S23_printFromTopToBottom();
    printFromTopToBottom.printFromTopToBottom(head);
  }
}
