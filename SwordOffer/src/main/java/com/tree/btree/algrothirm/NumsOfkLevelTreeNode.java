package com.tree.btree.algrothirm;

import com.haifei.model.TreeNode;

/**
 * Create by haifei on 7/1/2019 3:13 PM.
 * 二叉树中第k层的节点
 */
public class NumsOfkLevelTreeNode {

  public int numsOfKLevelTreeNode(TreeNode node, int k, int current) {
    if (node == null) {
      return 0;
    }

    if (k == current) {
      return 1;
    }

    return numsOfKLevelTreeNode(node.left, k, current + 1) + numsOfKLevelTreeNode(node.right, k,
      current + 1);
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

    final NumsOfkLevelTreeNode numsOfkLevelTreeNode = new NumsOfkLevelTreeNode();
    System.out.println(numsOfkLevelTreeNode.numsOfKLevelTreeNode(node1, 3, 1));
  }
}
