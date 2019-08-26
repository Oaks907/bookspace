package S1001_S1100;

import model.TreeNode;
import org.junit.Before;
import org.junit.Test;

/**
 * Create by haifei on 7/4/2019 10:35 AM.
 */
public class S1022_SumofRootToLeafBinaryNumbers {

  public int sumRootToLeaf(TreeNode root) {

    if (root == null) {
      return 0;
    }

    return helper(root, 0);
  }

  private int helper(TreeNode root, int sum) {

    sum = (sum << 1) + root.val;

    if (root.left == null && root.right == null) {
      return sum;
    }

    int left = root.left != null ? helper(root.left, sum) : 0;
    int right = root.right != null ? helper(root.right, sum) : 0;

    return left + right;
  }

  S1022_SumofRootToLeafBinaryNumbers sum;

  @Before
  public void before() {
    sum = new S1022_SumofRootToLeafBinaryNumbers();
  }


  @Test
  public void test1() {

    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(0);
    TreeNode node3 = new TreeNode(1);
    TreeNode node4 = new TreeNode(0);
    TreeNode node5 = new TreeNode(1);
    TreeNode node6 = new TreeNode(0);
    TreeNode node7 = new TreeNode(1);

    node1.left = node2;
    node1.right = node3;

    node2.left = node4;
    node2.right = node5;

    node3.left = node6;
    node3.right = node7;

    System.out.println(sum.sumRootToLeaf(node1));
  }
}
