package S101_200;

import model.Node;
import model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Create by haifei on 14/1/2019 12:00 AM.
 */
public class S104_MaximumDepthofBinaryTree {

  /**
   * 递归解法
   *
   * @param root
   * @return
   */
  public int maxDepth_Recursion(TreeNode root) {

    if (null == root) {
      return 0;
    }

    int left = maxDepth_Recursion(root.left);
    int right = maxDepth_Recursion(root.right);

    return Math.max(left, right) + 1;
  }

  /**
   * 广度解法
   *
   * @param root
   * @return
   */
  public int maxDepth_BFS(TreeNode root) {

    if (null == root) {
      return 0;
    }

    int depth = 0;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {

      int size = queue.size();
      depth++;

      for (int i = 0; i < size; i++) {

        final TreeNode poll = queue.poll();

        if (null != poll.left) {
          queue.offer(poll.left);
        }
        if (null != poll.right) {
          queue.offer(poll.right);
        }
      }
    }
    return depth;
  }

  /**
   * 深度优先遍历解法
   *
   * @param root
   * @return
   */
  public int maxDepth_DFS(TreeNode root) {

    if (null == root) {
      return 0;
    }

    return maxLevel(root, 0);
  }

  public int maxLevel(TreeNode node, int depth) {
    if (null == node) {
      return depth;
    }

    depth++;
    final int left = maxLevel(node.left, depth);
    final int right = maxLevel(node.right, depth);

    return Math.max(left, right);
  }

  public static void main(String[] args) {

    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    TreeNode node7 = new TreeNode(7);
    TreeNode node8 = new TreeNode(8);
    TreeNode node9 = new TreeNode(9);
    TreeNode node0 = new TreeNode(0);

    node6.left = node2;
    node6.right = node8;

    node2.left = node0;
    node2.right = node4;

    node4.left = node3;
    node4.right = node5;

    node8.left = node7;
    node8.right = node9;

    final S104_MaximumDepthofBinaryTree binaryTree =
      new S104_MaximumDepthofBinaryTree();
    System.out.println("递归解法:");
    System.out.println(binaryTree.maxDepth_Recursion(node6));
    System.out.println("广度优先BFS解法：");
    System.out.println(binaryTree.maxDepth_BFS(node6));
    System.out.println("深度优先DFS解法：");
    System.out.println(binaryTree.maxDepth_DFS(node6));
  }
}
