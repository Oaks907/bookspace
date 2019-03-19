package S101_200;

import model.TreeNode;
import utils.PrintUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Create by haifei on 15/7/2018.
 */
public class S102_levelOrder {

  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    Queue<TreeNode> queue = new ArrayDeque<>();

    queue.add(root);

    while (!queue.isEmpty()) {
      int levelNumber = queue.size();
      List<Integer> list = new ArrayList<>();

      for (int i = 0; i < levelNumber; i++) {
        TreeNode topNode = queue.remove();
        if (topNode.left != null) {
          queue.add(topNode.left);
        }
        if (topNode.right != null) {
          queue.add(topNode.right);
        }
        list.add(topNode.val);
      }
      result.add(list);
    }

    return result;
  }

  /**
   * 使用深度优先完成这个
   *
   * @param root
   * @return
   */
  public List<List<Integer>> levelOrder_DFS(TreeNode root) {

    List<List<Integer>> result = new ArrayList<>();

    if (root == null) {
      return result;
    }

    int depth = findDepth(root);

    int[][] arr = new int[depth][(int) Math.pow(2, depth - 1)];

    DFS(root, 0, 0, arr);

    PrintUtils.printArray(arr);

    return result;
  }

  private int findDepth(TreeNode head) {
    if (null == head) {
      return 0;
    }
    int left = findDepth(head.left);
    int right = findDepth(head.right);

    return Math.max(left, right) + 1;
  }

  private void DFS(TreeNode node, int depth, int col, int[][] arr) {
    if (null == node) {
      return;
    }

    arr[depth][col] = node.val;

    depth++;
    DFS(node.left, depth, col, arr);
    DFS(node.right, depth, col + 1, arr);
  }

  public static void main(String[] args) {
    TreeNode head = new TreeNode(8);
    TreeNode val6 = new TreeNode(6);
    TreeNode val5 = new TreeNode(5);
    TreeNode val7 = new TreeNode(7);
    TreeNode val10 = new TreeNode(10);
    TreeNode val9 = new TreeNode(9);
    TreeNode val11 = new TreeNode(11);

    head.left = val6;
    head.right = val10;
    val6.left = val5;
    val6.right = val7;
    val10.left = val9;
    val10.right = val11;

    S102_levelOrder levelOrder = new S102_levelOrder();
    levelOrder.levelOrder(head);

    levelOrder.levelOrder_DFS(head);
  }
}
