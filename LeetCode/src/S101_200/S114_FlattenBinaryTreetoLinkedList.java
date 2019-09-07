package S101_200;

import model.TreeNode;
import utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Create by haifei on 6/9/2019 8:06 AM.
 */
public class S114_FlattenBinaryTreetoLinkedList {

  // solution 1
  public void flatten(TreeNode root) {
    if (null == root) {
      return;
    }
    helper(root);
  }

  public void helper(TreeNode node) {
    if (null == node) {
      return;
    }
    if (node.left != null) {
      helper(node.left);
    }
    if (node.right != null) {
      helper(node.right);
    }

    TreeNode tmp = node.right;
    node.right = node.left;
    node.left = null;
    while (node.right != null) {
      node = node.right;
    }
    node.right = tmp;
  }

  // solution 2
  public void flatten2(TreeNode root) {
    if (null == root) {
      return;
    }
    TreeNode curNode = root;
    while (curNode != null) {
      if (curNode.left != null) {
        TreeNode tmp = curNode.right;
        curNode.right = curNode.left;
        curNode.left = null;

        TreeNode node = curNode.right;
        while (node.right != null) {
          node = node.right;
        }

        node.right = tmp;
      }
      curNode = curNode.right;
    }
  }

  public static void main(String[] args) {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    TreeNode node7 = new TreeNode(7);

    node4.left = node2;
    node4.right = node6;

    node2.left = node1;
    node2.right = node3;

    node6.left = node5;
    node6.right = node7;

    S114_FlattenBinaryTreetoLinkedList treetoLinkedList =
      new S114_FlattenBinaryTreetoLinkedList();

    treetoLinkedList.flatten(node4);
    PrintUtils.printTree(node4);
  }
}
