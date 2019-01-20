package S901_S1000;

import model.TreeNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.nio.cs.ext.MacHebrew;

/**
 * Create by haifei on 20/1/2019 11:45 AM.
 */
public class S979_DistributeCoinsinBinaryTree {

  public int distributeCoins(TreeNode root) {
    if (null == root) {
      return 0;
    }

    helper(root);

    return step;
  }

  public int step = 0;

  public int helper(TreeNode root) {

    if (root == null) {
      return 0;
    }

    int leftVal = 0;
    if (null != root.left) {
      leftVal = helper(root.left);
      step += leftVal >= 0 ? leftVal : -leftVal;
    }

    int rightVal = 0;
    if (null != root.right) {
      rightVal = helper(root.right);
      step += rightVal >= 0 ? rightVal : -rightVal;
    }

    return root.val + leftVal + rightVal - 1;
  }

  /**
   * 上面递归的优化
   *
   * @param root
   * @return
   */
  public int dfs(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int leftVal = helper(root.left);
    int rightVal = helper(root.right);

    step += Math.abs(leftVal) + Math.abs(rightVal);

    return root.val + leftVal + rightVal - 1;
  }


  @Before
  public void before() {
    step = 0;
  }

  @Test
  public void test0() {
    TreeNode node1 = new TreeNode(3);
    TreeNode node2 = new TreeNode(0);
    TreeNode node3 = new TreeNode(0);

    node1.left = node2;
    node1.right = node3;


    final S979_DistributeCoinsinBinaryTree binaryTree =
      new S979_DistributeCoinsinBinaryTree();

    Assert.assertEquals(2, binaryTree.distributeCoins(node1));
  }

  @Test
  public void test1() {
    TreeNode node1 = new TreeNode(0);
    TreeNode node2 = new TreeNode(3);
    TreeNode node3 = new TreeNode(0);

    node1.left = node2;
    node1.right = node3;


    final S979_DistributeCoinsinBinaryTree binaryTree =
      new S979_DistributeCoinsinBinaryTree();

    Assert.assertEquals(3, binaryTree.distributeCoins(node1));
  }


  @Test
  public void test2() {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(0);
    TreeNode node3 = new TreeNode(2);

    node1.left = node2;
    node1.right = node3;


    final S979_DistributeCoinsinBinaryTree binaryTree =
      new S979_DistributeCoinsinBinaryTree();

    Assert.assertEquals(2, binaryTree.distributeCoins(node1));
  }


  @Test
  public void test3() {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(0);
    TreeNode node3 = new TreeNode(0);
    TreeNode node4 = new TreeNode(3);

    node1.left = node2;
    node1.right = node3;
    node2.right = node4;


    final S979_DistributeCoinsinBinaryTree binaryTree =
      new S979_DistributeCoinsinBinaryTree();

    Assert.assertEquals(4, binaryTree.distributeCoins(node1));
  }

  @Test
  public void test4() {

    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(1);

    node1.right = node2;
//    node2.right = node4;

    final S979_DistributeCoinsinBinaryTree binaryTree =
      new S979_DistributeCoinsinBinaryTree();

    Assert.assertEquals(0, binaryTree.distributeCoins(node1));
  }

  @Test
  public void test5() {
    TreeNode node1 = new TreeNode(4);
    TreeNode node2 = new TreeNode(0);
    TreeNode node3 = new TreeNode(0);
    TreeNode node4 = new TreeNode(0);


    node1.left = node2;

    node2.right = node3;

    node3.right = node4;


    final S979_DistributeCoinsinBinaryTree binaryTree =
      new S979_DistributeCoinsinBinaryTree();

    Assert.assertEquals(6, binaryTree.distributeCoins(node1));
  }

}
