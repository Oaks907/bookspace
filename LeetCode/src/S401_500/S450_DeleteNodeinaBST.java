package S401_500;

import com.sun.tools.hat.internal.model.Root;
import model.TreeNode;
import org.junit.Test;
import utils.PrintUtils;

import javax.swing.*;

/**
 * Create by haifei on 15/9/2019 6:29 PM.
 */
public class S450_DeleteNodeinaBST {

  public TreeNode deleteNode(TreeNode root, int key) {
    return delete(root, key);
  }

  public TreeNode delete(TreeNode node, int key) {
    if (node == null) {
      return null;
    }

    if (key > node.val) {
      node.right = delete(node.right, key);
    } else if (key < node.val) {
      node.left = delete(node.left, key);
    } else {

      if (node.right == null) {
        return node.left;
      }

      TreeNode cur = node.right;
      while (cur.left != null) {
        cur = cur.left;
      }
      cur.left = node.left;

      return node.right;
    }
    return node;
  }

  @Test
  public void test() {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    TreeNode node7 = new TreeNode(7);
    TreeNode node8 = new TreeNode(8);

    node5.left = node3;
    node5.right = node6;

    node3.left = node2;
    node3.right = node4;

    node6.right = node7;

    TreeNode result = deleteNode(node5, 3);

    PrintUtils.printTree(result);
  }

  @Test
  public void test1() {
    TreeNode node0 = new TreeNode(0);

    TreeNode result = deleteNode(node0, 0);

    PrintUtils.printTree(result);
  }

  @Test
  public void test2() {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);

    node1.right = node2;

    TreeNode result = deleteNode(node1, 2);

    PrintUtils.printTree(result);
  }

  @Test
  public void test3() {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);

    node2.left = node1;
    node2.right = node3;

    TreeNode result = deleteNode(node2, 3);

    PrintUtils.printTree(result);
  }
}
