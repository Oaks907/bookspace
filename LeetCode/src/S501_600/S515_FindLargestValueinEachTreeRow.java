package S501_600;

import model.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Create by haifei on 29/8/2019 11:59 PM.
 */
public class S515_FindLargestValueinEachTreeRow {
  public List<Integer> largestValues(TreeNode root) {

    List<Integer> result = new ArrayList<>();

    if (null == root) {
      return result;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      int temp = Integer.MIN_VALUE;
      while (size-- > 0) {
        TreeNode poll = queue.poll();
        temp = Math.max(temp, poll.val);
        if (poll.left != null) {
          queue.offer(poll.left);
        }
        if (poll.right != null) {
          queue.offer(poll.right);
        }
      }
      result.add(temp);
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

    S515_FindLargestValueinEachTreeRow treeRow =
      new S515_FindLargestValueinEachTreeRow();

    System.out.println(treeRow.largestValues(node1));
  }
}
