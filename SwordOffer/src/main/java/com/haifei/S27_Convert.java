package com.haifei;

import com.haifei.model.TreeNode;

/**
 * Create by haifei on 6/8/2018.
 */
public class S27_Convert {

  //SwordOffer中这是代码中的局部变量，使用存在问题，这个定义为全局，可以试下原来的问题代码
  TreeNode lastOfNode = null;


  private TreeNode convert(TreeNode rootOfTree) {
    if (null == rootOfTree) {
      return  rootOfTree;
    }


    convertNode(rootOfTree);

    while (lastOfNode != null && lastOfNode.left != null) {
      lastOfNode = lastOfNode.left;
    }
    return lastOfNode;
  }

  void convertNode(TreeNode node) {
    if (node == null) {
      return;
    }

    TreeNode currentNode = node;
    if (currentNode.left != null) {
      convertNode(currentNode.left);
    }
    currentNode.left = lastOfNode;

    if (lastOfNode != null) {
      lastOfNode.right = currentNode;
    }

    lastOfNode = currentNode;

    if (currentNode.right != null) {
      convertNode(currentNode.right);
    }

  }


  public static void main(String[] args) {
    TreeNode node4 = new TreeNode(4);
    TreeNode node6 = new TreeNode(6);
    TreeNode node8 = new TreeNode(8);
    TreeNode node10 = new TreeNode(10);
    TreeNode node12 = new TreeNode(12);
    TreeNode node14 = new TreeNode(14);
    TreeNode node16 = new TreeNode(16);

    node6.left = node4;
    node6.right = node8;
    node14.left = node12;
    node14.right = node16;
    node10.left = node6;
    node10.right = node14;


    TreeNode node = new S27_Convert().convert(node10);
    while (node != null) {
      System.out.print(node.val + " ");
      node = node.right;
    }
  }

  private TreeNode head = null;
  private TreeNode right = null;
}
