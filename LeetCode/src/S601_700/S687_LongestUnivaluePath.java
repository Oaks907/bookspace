package S601_700;

import model.TreeNode;

/**
 * Create by haifei on 5/1/2018.
 * https://leetcode.com/problems/longest-univalue-path/
 */
public class S687_LongestUnivaluePath {

  int longestPath = 0;

  public int longestUnivaluePath(TreeNode root) {
    dfs(root);
    return longestPath;
  }

  private int dfs(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int left = dfs(node.left);
    int right = dfs(node.right);

    int longestLeft = 0;
    int longestRight = 0;

    if (node.left != null && node.left.val == node.val) {
      longestLeft = left + 1;
    }

    if (node.right != null && node.right.val == node.val) {
      longestRight = right + 1;
    }

    longestPath = Math.max(longestPath, longestLeft + longestRight);

    return Math.max(longestLeft, longestRight);
  }

  public static void main(String[] args) {
    TreeNode node1 = new TreeNode(1);
    TreeNode node4_1 = new TreeNode(4);
    TreeNode node4_2 = new TreeNode(4);
    TreeNode node4_3 = new TreeNode(4);
    TreeNode node4_4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node5_1 = new TreeNode(5);

    node1.left = node4_1;
    node1.right = node5;

    node4_1.left = node4_2;
    node4_1.right = node4_3;

    node4_2.left = node4_4;

    node5.right = node5_1;

    final S687_LongestUnivaluePath longestUnivaluePath = new S687_LongestUnivaluePath();
    System.out.println(longestUnivaluePath.longestUnivaluePath(node1));

  }

}
