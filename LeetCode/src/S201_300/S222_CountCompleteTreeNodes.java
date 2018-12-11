package S201_300;

import com.sun.xml.internal.stream.writers.WriterUtility;
import model.TreeNode;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Create by haifei on 11/12/2018 7:37 AM.
 * https://leetcode.com/problems/count-complete-tree-nodes/
 */
public class S222_CountCompleteTreeNodes {

  //直接递归查找，会时间超限
  public int countNodes(TreeNode root) {
    if (null == root) {
      return 0;
    }
    int left = countNodes(root.left);
    int right = countNodes(root.right);
    return left + right + 1;
  }

  //因为是完全二叉树，所以先统计层数，再加上叶子节点
  public int countNodes2(TreeNode root) {
    int level = countLevel(root);
    int leafNode = countLeafNode(root, level, 1);
    return (int) (Math.pow(2, level - 1) - 1 + leafNode);
  }

  public int countLevel(TreeNode root) {
    if (null == root) {
      return 0;
    }
    return countLevel(root.left) + 1;
  }

  public int countLeafNode(TreeNode root, int level, int cur) {
    if (root == null) {
      return 0;
    }

    final int leafNode = countLeafNode(root.left, level, cur + 1);
    final int rightNode = countLeafNode(root.right, level, cur + 1);

    if (cur == level) {
      return leafNode + rightNode + 1;
    } else {
      return leafNode + rightNode;
    }
  }

  //上面还是会时间超限。使用非递归方法
  public int countNodes3(TreeNode root) {
    if (root == null) return 0;

    int count = 0;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      count++;
      if (node.left != null) {
        queue.add(node.left);
      }
      if (node.right != null) {
        queue.add(node.right);
      }
    }
    return count;
  }

  public static void main(String[] args) {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    node1.left = node2;
    node1.right = node3;
    node2.left = node4;
    node2.right = node5;
    node3.left = node6;

    final S222_CountCompleteTreeNodes countCompleteTreeNodes =
      new S222_CountCompleteTreeNodes();
//    System.out.println(countCompleteTreeNodes.countLevel(node1));
//    System.out.println(countCompleteTreeNodes.countLeafNode(node1, 3, 1));
//    System.out.println(countCompleteTreeNodes.countNodes2(node1));


    System.out.println(countCompleteTreeNodes.countNodes2(node4));

    System.out.println(countCompleteTreeNodes.countNodes3(node1));
    System.out.println(countCompleteTreeNodes.countNodes3(node4));
  }
}
