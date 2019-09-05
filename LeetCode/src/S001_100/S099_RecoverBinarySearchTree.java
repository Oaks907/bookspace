package S001_100;

import model.TreeNode;
import org.junit.Test;

/**
 * https://leetcode.com/problems/recover-binary-search-tree/
 * Create by haifei on 5/9/2019 8:12 AM.
 */
public class S099_RecoverBinarySearchTree {

  // solution 2
  TreeNode first = null;
  TreeNode second = null;
  TreeNode prev = null;

  public void recoverTree(TreeNode root) {
    inOrder(root);
    int temp = first.val;
    first.val = second.val;
    second.val = temp;
  }

  private void inOrder(TreeNode node) {
    if (node == null) {
      return;
    }
    inOrder(node.left);

    if (prev != null && prev.val > node.val) {
      if (first == null) {
        first = prev;
        second = node;
      } else {
        second = node;
      }
    }
    prev = node;
    inOrder(node.right);
  }

  @Test
  public void test() {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);

    node3.left = node1;
    node3.right = node4;
    node4.left = node2;

    S099_RecoverBinarySearchTree recoverBinarySearchTree = new S099_RecoverBinarySearchTree();
    recoverBinarySearchTree.recoverTree(node3);
  }

  @Test
  public void test1() {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);

    node2.right = node1;

    S099_RecoverBinarySearchTree recoverBinarySearchTree = new S099_RecoverBinarySearchTree();
    recoverBinarySearchTree.recoverTree(node2);
  }

}
