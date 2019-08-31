package S001_100;

import model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Create by haifei on 30/8/2019 8:27 AM.
 */
public class S094_BinaryTreeInorderTraversal {

  public List<Integer> inorderTraversal(TreeNode root) {

    List<Integer> result = new ArrayList<>();

    Stack<TreeNode> stack = new Stack<>();
    while (!stack.isEmpty() || null != root) {
      while (root != null) {
        stack.push(root);
        root = root.left;
      }

      TreeNode pop = stack.pop();
      result.add(pop.val);
      root = pop.right;
    }

    return result;
  }

  public static void main(String[] args) {
    TreeNode node0 = new TreeNode(0);
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    TreeNode node7 = new TreeNode(7);
    TreeNode node8 = new TreeNode(8);

    node1.left = node3;
    node1.right = node2;

    node3.left = node5;
    node3.right = node4;

    node2.right = node8;

    S094_BinaryTreeInorderTraversal inorderTraversal =
      new S094_BinaryTreeInorderTraversal();

    System.out.println(inorderTraversal.inorderTraversal(node1));
  }
}
