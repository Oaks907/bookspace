package S801_S900;

import model.TreeNode;
import org.junit.Before;
import org.junit.Test;

/**
 * https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
 * Create by haifei on 30/8/2019 11:28 PM.
 */
public class S865_SmallestSubtreewithDeepestNodes {

  public TreeNode subtreeWithAllDeepest(TreeNode root) {
    helper(root, 1);
    return result;
  }

  private int maxDepth = Integer.MIN_VALUE;
  private TreeNode result;

  private int helper(TreeNode node, int depthFromRoot) {
    if (null == node) {
      return 0;
    }

    int leftDepth = helper(node.left, depthFromRoot + 1);
    int rightDepth = helper(node.right, depthFromRoot + 1);

    if (leftDepth == rightDepth && maxDepth <= leftDepth + depthFromRoot) {
      result = node;
      maxDepth = depthFromRoot + leftDepth;
    }

    return Math.max(leftDepth, rightDepth) + 1;
  }

  private boolean isLeaf(TreeNode node) {
    return node.left == null && node.right == null;
  }


  TreeNode node0;
  TreeNode node1;
  TreeNode node2;
  TreeNode node3;
  TreeNode node4;
  TreeNode node5;
  TreeNode node6;
  TreeNode node7;
  TreeNode node8;
  S865_SmallestSubtreewithDeepestNodes deepestNodes;

  @Before
  public void before() {
    node0 = new TreeNode(0);
    node1 = new TreeNode(1);
    node2 = new TreeNode(2);
    node3 = new TreeNode(3);
    node4 = new TreeNode(4);
    node5 = new TreeNode(5);
    node6 = new TreeNode(6);
    node7 = new TreeNode(7);
    node8 = new TreeNode(8);

    deepestNodes =
      new S865_SmallestSubtreewithDeepestNodes();
    ;
  }

  @Test
  public void test2() {
    node0.left = node2;
    node0.right = node1;

    node1.left = node3;

    System.out.println(deepestNodes.subtreeWithAllDeepest(node0).val);
  }

  @Test
  public void test1() {

    node3.left = node5;
    node3.right = node1;

    node5.left = node6;
    node5.right = node2;

    node2.left = node7;
    node2.right = node4;

    node1.left = node0;
    node1.right = node8;

    System.out.println(deepestNodes.subtreeWithAllDeepest(node3).val);
  }
}
