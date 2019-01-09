package com.tree.btree.algrothirm;

import com.haifei.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Create by haifei on 8/1/2019 5:35 PM.
 * 判断是否是完全二叉树
 */
public class IsCompleteTreeNode {

  public boolean isCompleteTreeNode(TreeNode root) {

    if (root == null) {
      return true;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    boolean result = true;
    boolean hasNoChildren = false;

    while (queue.isEmpty()) {
      final TreeNode node = queue.remove();

      if (hasNoChildren) {
        if (node.left != null || node.right != null) {
          result = false;
          break;
        }
      } else {
        if (node.left != null && node.right != null) {
          queue.add(node.left);
          queue.add(node.right);
        } else if (node.left != null && node.right == null) {
          hasNoChildren = true;
          queue.add(node.left);
        } else if (node.left == null && node.right != null) {
          result = false;
          break;
        } else {
          hasNoChildren = true;
        }
      }
    }

    return result;
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

    final IsCompleteTreeNode isCompleteTreeNode = new IsCompleteTreeNode();
    System.out.println(isCompleteTreeNode.isCompleteTreeNode(node1));
  }
}
