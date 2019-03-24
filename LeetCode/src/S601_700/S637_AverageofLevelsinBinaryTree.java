package S601_700;

import model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Create by haifei on 23/3/2019 6:17 PM.
 * <p>
 * https://leetcode.com/problems/average-of-levels-in-binary-tree/
 * <p>
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form
 * of an array.
 */
public class S637_AverageofLevelsinBinaryTree {

  public List<Double> averageOfLevels(TreeNode root) {

    List<Double> result = new ArrayList<>();

    if (root == null) {
      return result;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      double sum = 0;
      int count = size;

      while (size-- > 0) {
        final TreeNode poll = queue.poll();
        sum += poll.val;

        if (poll.left != null) {
          queue.offer(poll.left);
        }
        if (poll.right != null) {
          queue.offer(poll.right);
        }
      }

      result.add(sum / count);
    }

    return result;
  }

  @Test
  public void test() {

    TreeNode node3 = new TreeNode(3);
    TreeNode node9 = new TreeNode(9);
    TreeNode node20 = new TreeNode(20);
    TreeNode node15 = new TreeNode(15);
    TreeNode node17 = new TreeNode(17);

    node3.left = node9;
    node3.right = node20;

    node20.left = node15;
    node20.right = node17;

    final S637_AverageofLevelsinBinaryTree levelsinBinaryTree =
      new S637_AverageofLevelsinBinaryTree();

    System.out.println(levelsinBinaryTree.averageOfLevels(node3));
  }
}
