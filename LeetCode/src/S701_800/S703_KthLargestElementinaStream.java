package S701_800;

import java.awt.image.Kernel;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Create by haifei on 13/1/2019 12:11 AM.
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
 * 第k大的数
 */
public class S703_KthLargestElementinaStream {

  Queue<Integer> priorityQueue;
  int size;

  public S703_KthLargestElementinaStream(int k, int[] nums) {
    priorityQueue = new PriorityQueue(k);
    size = k;
    for (int i : nums) {
      this.add(i);
    }
  }

  public int add(int val) {
    if (priorityQueue.size() < size) {
      priorityQueue.add(val);
    } else if (priorityQueue.peek() < val) {
      priorityQueue.poll();
      priorityQueue.add(val);
    }

    return priorityQueue.peek();
  }

  public static void main(String[] args) {

    int k = 3;
    int[] arr = {4, 5, 8, 2};
    S703_KthLargestElementinaStream kthLargest = new S703_KthLargestElementinaStream(3, arr);
    System.out.println(kthLargest.add(3));
    System.out.println(kthLargest.add(5));
    System.out.println(kthLargest.add(10));
    System.out.println(kthLargest.add(9));
    System.out.println(kthLargest.add(4));
  }
}
