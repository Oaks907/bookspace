package S201_300;

import model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 24/3/2019 8:35 PM.
 * <p>
 * https://leetcode.com/problems/binary-tree-paths/
 */
public class S257_BinaryTreePaths {

  List<String> result = new ArrayList<>();

  public List<String> binaryTreePaths(TreeNode root) {

    helper(root, "");

    return result;
  }

  private void helper(TreeNode root, String sb) {

    if (root == null) {
      return;
    }

    sb += (root.val + "->");

    if (root.left == null && root.right == null) {
      if (sb.length() > 0) {
        sb = sb.substring(0, sb.length() - 2);
        result.add(sb);
      }
      return;
    }

    helper(root.left, new String(sb));
    helper(root.right, new String(sb));
  }

  @Test
  public void test() {

    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);

    node1.left = node2;
    node1.right = node3;

    node2.left = node4;
    node2.right = node5;

    final S257_BinaryTreePaths binaryTreePaths = new S257_BinaryTreePaths();
    System.out.println(binaryTreePaths.binaryTreePaths(node1));
  }


}
