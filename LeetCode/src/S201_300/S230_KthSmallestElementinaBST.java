package S201_300;

import model.TreeNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

/**
 * Create by haifei on 31/8/2019 6:03 PM.
 */
public class S230_KthSmallestElementinaBST {

  public int kthSmallest(TreeNode root, int k) {

    Stack<TreeNode> stack = new Stack<>();
    TreeNode lastVisit = root;
    int count = 0;
    while (!stack.isEmpty() || root != null) {

      while (root != null) {
        stack.push(root);
        root = root.left;
      }

      if (!stack.isEmpty()) {
        root = stack.pop();

        if (++count == k) {
          return root.val;
        }

        root = root.right;
      }
    }

    return -1;
  }

  TreeNode node1;
  TreeNode node2;
  TreeNode node3;
  TreeNode node4;
  TreeNode node5;
  TreeNode node6;
  TreeNode node7;
  TreeNode node8;

  @Before
  public void before() {
    node1 = new TreeNode(1);
    node2 = new TreeNode(2);
    node3 = new TreeNode(3);
    node4 = new TreeNode(4);
    node5 = new TreeNode(5);
    node6 = new TreeNode(6);
    node7 = new TreeNode(7);
    node8 = new TreeNode(8);
  }

  @Test
  public void test() {
    node3.left = node1;
    node3.right = node4;

    node1.right = node2;

    S230_KthSmallestElementinaBST elementinaBST =
      new S230_KthSmallestElementinaBST();

    Assert.assertEquals(1, elementinaBST.kthSmallest(node3, 1));
  }

  @Test
  public void test2() {
    node5.left = node3;
    node5.right = node6;

    node3.left = node2;
    node3.right = node4;

    node2.left = node1;

    S230_KthSmallestElementinaBST elementinaBST =
      new S230_KthSmallestElementinaBST();

    Assert.assertEquals(3,elementinaBST.kthSmallest(node5, 3));
  }

  @Test
  public void test3() {
    S230_KthSmallestElementinaBST elementinaBST =
      new S230_KthSmallestElementinaBST();

    System.out.println(elementinaBST.kthSmallest(node5, 1));
  }
}
