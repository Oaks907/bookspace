package S101_200;

import model.TreeNode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Create by haifei on 1/9/2019 12:17 AM.
 */
public class S144_BinaryTreePreorderTraversal {

  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();

    while (!stack.isEmpty() || root != null) {
      while (root != null) {
        result.add(root.val);
        stack.push(root);
        root = root.left;
      }

      if (!stack.isEmpty()) {
        TreeNode pop = stack.pop();
        root = pop.right;
      }
    }

    return result;
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

    S144_BinaryTreePreorderTraversal preorderTraversal =
      new S144_BinaryTreePreorderTraversal();

    System.out.println(preorderTraversal.preorderTraversal(node3));
  }
}
