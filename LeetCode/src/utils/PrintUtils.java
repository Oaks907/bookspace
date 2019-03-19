package utils;


import model.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Create by haifei on 24/1/2018.
 */
public class PrintUtils {

  private static final int MAXIMUM_CAPACITY = 10000;

  public static void printArray(int[][] nums) {
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

    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.add(node);

    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size-- > 0) {

        final TreeNode poll = queue.poll();

        System.out.print(poll.val + " ");

        if (poll.left != null) {
          queue.add(poll.left);
        }

        if (poll.right != null) {
          queue.add(poll.right);
        }
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    System.out.println(tableSizeFor(8));
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
