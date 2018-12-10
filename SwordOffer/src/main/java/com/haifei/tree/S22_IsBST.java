package com.haifei.tree;

import com.haifei.model.TreeNode;

/**
 * Create by haifei on 17/11/2018 11:05 PM.
 * 一棵BST定义为：
 * 节点的左子树中的值要严格小于该节点的值。
 * 节点的右子树中的值要严格大于该节点的值。
 * 左右子树也必须是二叉查找树。
 * 一个节点的树也是二叉查找树。
 */
public class S22_IsBST {

  public int lastVal = Integer.MAX_VALUE;
  public boolean firstNode = true;

  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    if (!isValidBST(root.left)) {
      return false;
    }
    if (!firstNode && lastVal >= root.val) {
      return false;
    }
    firstNode = false;
    lastVal = root.val;
    if (!isValidBST(root.right)) {
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    TreeNode node7 = new TreeNode(7);
    TreeNode node8 = new TreeNode(8);

    node5.left = node3;
    node5.right = node7;
    node3.left = node2;
    node3.right = node4;
    node7.left = node6;
    node7.right = node8;

    System.out.println(new S22_IsBST().isValidBST(node5));
  }
}
