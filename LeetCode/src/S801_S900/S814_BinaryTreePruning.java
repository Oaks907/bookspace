package S801_S900;

import model.Node;
import model.TreeNode;
import utils.PrintUtils;

import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-pruning/
 * Create by haifei on 4/7/2019 9:03 AM.
 */
public class S814_BinaryTreePruning {

  public TreeNode pruneTree(TreeNode root) {

    final TreeNode node = new TreeNode(1);
    node.left = root;
    helper(root, node, true);
    return node.left;
  }

  private void helper(TreeNode node, TreeNode parent, boolean isLeft) {
    if (null == node) {
      return;
    }
    helper(node.left, node, true);
    helper(node.right, node, false);

    if (node.val == 0 && null == node.left && null == node.right) {
      if (isLeft) {
        parent.left = null;
      } else {
        parent.right = null;
      }
    }
  }


  public static void main(String[] args) {
    TreeNode node00 = new TreeNode(0);
    TreeNode node01 = new TreeNode(0);
    TreeNode node02 = new TreeNode(0);
    TreeNode node03 = new TreeNode(0);
    TreeNode node10 = new TreeNode(1);
    TreeNode node11 = new TreeNode(1);
    TreeNode node12 = new TreeNode(1);

    node10.left = node00;
    node10.right = node11;

    node00.left = node01;
    node00.right = node02;

    node11.left = node03;
    node11.right = node12;

    final TreeNode node = new S814_BinaryTreePruning().pruneTree(node10);
    PrintUtils.printTree(node);
  }
}
