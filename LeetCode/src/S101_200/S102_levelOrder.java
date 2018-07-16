package S101_200;

import org.w3c.dom.ls.LSException;
import utils.TreeNode;

import javax.swing.*;
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
  }
}
