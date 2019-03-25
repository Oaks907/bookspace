package S101_200;

import model.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Create by haifei on 24/3/2019 8:25 PM.
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 */
public class S107_BinaryTreeLevelOrderTraversalII {

  public List<List<Integer>> levelOrderBottom(TreeNode root) {

    if (root == null) {
      return new ArrayList<>();
    }

    List<List<Integer>> result = new ArrayList<>();

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();

      List<Integer> subList = new ArrayList<>();

      while (size-- > 0) {

        final TreeNode poll = queue.poll();

        subList.add(poll.val);

        if (poll.left != null) {
          queue.offer(poll.left);
        }
        if (poll.right != null) {
          queue.offer(poll.right);
        }
      }

      result.add(0, subList);
    }

    return result;
  }
}
