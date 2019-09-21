package S101_200;

import model.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
 * Create by haifei on 16/9/2019 7:37 AM.
 */
public class S129_SumRoottoLeafNumbers {

  public int sumNumbers(TreeNode root) {
    if (null == root) {
      return 0;
    }
    sumRec(root, 0);
    return result;
  }

  int result = 0;

  private void sumRec(TreeNode node, int sum) {
    if (null == node) {
      return;
    }

    int temp = (sum + node.val) * 10;

    sumRec(node.left, temp);
    sumRec(node.right, temp);

    if (node.left == null && node.right == null) {
      result += sum + node.val;
    }
  }

  @Test
  public void test() {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);

    node1.left = node2;
    node1.right = node3;

    int result = sumNumbers(node1);

    Assert.assertEquals(25, result);
  }

  @Test
  public void test1() {
    TreeNode node0 = new TreeNode(0);
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    TreeNode node7 = new TreeNode(7);
    TreeNode node8 = new TreeNode(8);
    TreeNode node9 = new TreeNode(9);

    node4.left = node9;
    node4.right = node0;

    node9.left = node5;
    node9.right = node1;

    int result = sumNumbers(node4);

    Assert.assertEquals(1026, result);
  }
}
