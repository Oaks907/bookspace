package utils;


import model.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Create by haifei on 24/1/2018.
 */
public class PrintUtils {

  private static final int MAXIMUM_CAPACITY = 10000;

  public static void printListNode(ListNode node) {
    while (node != null) {
      System.out.print(node.val + " -> ");
      node = node.next;
    }
    System.out.println("NULL");
  }

  public static void printArray(int[][] nums) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < nums[0].length; j++) {
        System.out.print(nums[i][j] + ", ");
      }
      System.out.println();
    }
  }

  public static void printArrayString(String[][] nums) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < nums[0].length; j++) {
        System.out.print(nums[i][j] + ", ");
      }
      System.out.println();
    }
  }

  public static void printCharArray(char[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        System.out.print(arr[i][j] + ", ");
      }
      System.out.println();
    }
  }

  public static void printArray(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      System.out.print(nums[i] + ", ");
    }
    System.out.println();
  }

  public static void printArray(String[] nums) {
    for (int i = 0; i < nums.length; i++) {
      System.out.print(nums[i] + ", ");
    }
    System.out.println();
  }

  public static void printLinkedList(ListNode head) {
    while (head != null) {
      System.out.print(head.val + " ");
      head = head.next;
    }
    System.out.println();
  }

  public static void printTree(TreeNode node) {
    if (node == null) {
      System.out.println("node is null");
      return;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(node);

    while (!queue.isEmpty()) {
      int size = queue.size();
      if(queueNodeAllIsNull(queue)) {
        break;
      }
      while (size-- > 0) {

        final TreeNode poll = queue.poll();
        if (poll == null) {
          System.out.print("X ");
        } else {
          System.out.print(poll.val + " ");
        }

        if (poll != null) {
          queue.add(poll.left);
          queue.add(poll.right);
        }
      }
      System.out.println();
    }
  }

  private static boolean queueNodeAllIsNull(Queue<TreeNode> queue) {
    for (TreeNode node : queue) {
      if(null != node) {
        return false;
      }
    }
    return true;
  }

  public static TreeNode buildTree(Integer[] nums) {
    if (nums == null || nums.length == 0) {
      return null;
    }
    TreeNode root = new TreeNode(nums[0]);
    TreeNode[] nodes = new TreeNode[nums.length];
    nodes[0] = root;

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == null) {
        continue;
      }

      TreeNode node = new TreeNode(nums[i]);
      nodes[i] = node;

      if (i % 2 == 0) {
        System.out.println(i);
        nodes[(i - 2) / 2].right = node;
      } else {

        nodes[(i - 1) / 2].left = node;
      }
    }

    return root;
  }

  public static void main(String[] args) {
    Integer[] ints = {10, 5, -3, 3, 2, null, 11, 3, -2, null, 1};
    final TreeNode node = buildTree(ints);
    printTree(node);
  }

  static final int tableSizeFor(int cap) {
    int n = cap - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
  }
}
