package S101_200;

import model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Create by haifei on 6/9/2019 7:48 AM.
 */
public class S103_BinaryTreeZigzagLevelOrderTraversal {

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    boolean isLeftToRight = true;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      ArrayList<Integer> list = new ArrayList<>();

      while (size-- > 0) {
        TreeNode poll = queue.poll();
        if (poll == null) {
          continue;
        }
        list.add(poll.val);

        if (poll.left != null) {
          queue.offer(poll.left);
        }
        if (poll.right != null) {
          queue.offer(poll.right);
        }
      }

      if (!isLeftToRight) {
        Collections.reverse(list);
      }
      isLeftToRight = !isLeftToRight;

      result.add(list);
    }

    return result;
  }

  @Test
  public void test() {
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    TreeNode node7 = new TreeNode(7);
    TreeNode node8 = new TreeNode(8);

    node3.left = node8;
    node3.right = node4;

    node4.left = node2;
    node4.right = node7;

    S103_BinaryTreeZigzagLevelOrderTraversal zigzagLevelOrderTraversal =
      new S103_BinaryTreeZigzagLevelOrderTraversal();
    System.out.println(zigzagLevelOrderTraversal.zigzagLevelOrder(node3));
  }
}
