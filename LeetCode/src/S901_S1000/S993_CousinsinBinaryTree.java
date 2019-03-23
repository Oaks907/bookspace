package S901_S1000;

import model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Create by haifei on 23/3/2019 6:41 PM.
 *
 * https://leetcode.com/problems/cousins-in-binary-tree/
 *
 * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth
 * k+1.
 *
 * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
 *
 * We are given the root of a binary tree with unique values, and the values x and y of two
 * different nodes in the tree.
 *
 * Return true if and only if the nodes corresponding to the values x and y are cousins.
 *
 * 判断是否是 cousion 节点。
 * 定义 x y 节点同一级，但是父节点不同
 */
public class S993_CousinsinBinaryTree {

  public boolean isCousins(TreeNode root, int x, int y) {

    if (root == null) {
      return false;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    int xLevel = -1;
    int yLevel = -1;
    int currentLevel = 0;

    while (!queue.isEmpty()) {

      int size = queue.size();
      currentLevel++;

      while (size-- > 0) {
        final TreeNode poll = queue.poll();

        if (poll.left != null && poll.right != null) {
          if ((poll.left.val == x && poll.right.val == y) || (poll.left.val == y && poll.right.val == x)) {
            return false;
          }
        }

        if (poll.left != null) {
          queue.offer(poll.left);
        }
        if (poll.right != null) {
          queue.offer(poll.right);
        }

        if (poll.val == x) {
          xLevel = currentLevel;
        }

        if (poll.val == y) {
          yLevel = currentLevel;
        }
      }

      if (xLevel != -1 && yLevel != -1 && (xLevel == yLevel)) {
        return true;
      }
    }

    return false;
  }
}
