package S501_600;

import model.Node;
import model.TreeNode;
import org.junit.Test;
import utils.PrintUtils;

import java.util.Stack;

/**
 * Create by haifei on 23/3/2019 8:42 PM.
 * <p>
 * https://leetcode.com/problems/convert-bst-to-greater-tree/
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the
 * original BST is changed to the original key plus sum of all keys greater than the original key
 * in BST.
 */
public class S538_ConvertBSTtoGreaterTree {

  public TreeNode convertBST(TreeNode root) {
    if (root == null) {
      return null;
    }

    recursion(root, new int[1]);

    return root;
  }

  private void recursion(TreeNode root, int[] pre) {
    if (null == root) {
      return;
    }

    recursion(root.right, pre);
    pre[0] += root.val;
    root.val = pre[0];
    recursion(root.left, pre);
  }


  @Test
  public void test() {

    final S538_ConvertBSTtoGreaterTree greaterTree =
      new S538_ConvertBSTtoGreaterTree();

    TreeNode node2 = new TreeNode(2);
    TreeNode node5 = new TreeNode(5);
    TreeNode node13 = new TreeNode(13);

    node5.left = node2;
    node5.right = node13;

    PrintUtils.printTree(greaterTree.convertBST(node5));
  }

  @Test
  public void test2() {

    final S538_ConvertBSTtoGreaterTree greaterTree =
      new S538_ConvertBSTtoGreaterTree();

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

    PrintUtils.printTree(greaterTree.convertBST(node4));
  }

}
