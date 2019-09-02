package S101_200;

import model.TreeNode;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/
 * Create by haifei on 1/9/2019 10:09 AM.
 */
public class S173_BinarySearchTreeIterator {

  private int currentIndex;
  private List<TreeNode> postOrderList;

  public S173_BinarySearchTreeIterator(TreeNode root) {
    postOrderList = postOrder(root);
    System.out.println(postOrderList);
    currentIndex = 0;
  }

  private List<TreeNode> postOrder(TreeNode node) {
    Stack<TreeNode> stack = new Stack<>();
    List<TreeNode> list = new ArrayList<>();
    while (node != null || !stack.isEmpty()) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }

      if (!stack.isEmpty()) {
        TreeNode pop = stack.pop();
        list.add(pop);
        node = pop.right;
      }
    }
    return list;
  }

  /**
   * @return the next smallest number
   */
  public int next() {
    return postOrderList.get(currentIndex++).val;
  }

  /**
   * @return whether we have a next smallest number
   */
  public boolean hasNext() {
    return currentIndex < postOrderList.size();
  }

  public static void main(String[] args) {

    TreeNode node1;
    TreeNode node2;
    TreeNode node3;
    TreeNode node4;
    TreeNode node5;
    TreeNode node6;
    TreeNode node7;
    TreeNode node8;
    TreeNode node9;

    node1 = new TreeNode(1);
    node2 = new TreeNode(2);
    node3 = new TreeNode(3);
    node4 = new TreeNode(4);
    node5 = new TreeNode(5);
    node6 = new TreeNode(6);
    node7 = new TreeNode(7);
    node8 = new TreeNode(8);
    node9 = new TreeNode(9);

    TreeNode node15 = new TreeNode(15);
    TreeNode node20 = new TreeNode(20);

    node7.left = node3;
    node7.right = node15;

    node15.left = node9;
    node15.right = node20;

    S173_BinarySearchTreeIterator binarySearchTreeIterator =
      new S173_BinarySearchTreeIterator(node7);

    Assert.assertEquals(3, binarySearchTreeIterator.next());
    Assert.assertEquals(7, binarySearchTreeIterator.next());
    Assert.assertTrue(binarySearchTreeIterator.hasNext());
    Assert.assertEquals(9, binarySearchTreeIterator.next());
    Assert.assertEquals(15, binarySearchTreeIterator.next());
    Assert.assertTrue(binarySearchTreeIterator.hasNext());
    Assert.assertEquals(20, binarySearchTreeIterator.next());
    Assert.assertFalse(binarySearchTreeIterator.hasNext());
  }
}
