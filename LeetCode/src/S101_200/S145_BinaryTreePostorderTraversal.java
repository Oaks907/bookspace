package S101_200;

import com.sun.tools.hat.internal.model.Root;
import model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 * Create by haifei on 25/9/2019 8:18 AM.
 */
public class S145_BinaryTreePostorderTraversal {

  public List<Integer> postorderTraversal(TreeNode root) {

    List<Integer> result = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    TreeNode lastViewNode = root;

    while (root != null || !stack.isEmpty()) {
      while (root != null) {
        stack.push(root);
        root = root.left;
      }

      root = stack.peek();

      if (root.right == null || root.right == lastViewNode) {
        result.add(root.val);
        stack.pop();
        lastViewNode = root;
        root = null;
      } else {
        root = root.right;
      }
    }

    return result;
  }

  @Test
  public void test() {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);

    node1.left = node2;
    node1.right = node3;
    node2.left = node4;
    node2.right = node5;
    node3.left = node6;

    List<Integer> result = postorderTraversal(node1);

    System.out.println(result);
  }
}
