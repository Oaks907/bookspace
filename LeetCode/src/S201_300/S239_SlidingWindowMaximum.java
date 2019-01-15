package S201_300;

import utils.PrintUtils;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Create by haifei on 13/1/2019 2:58 PM.
 * <p>
 * https://leetcode.com/problems/sliding-window-maximum/
 * <p>
 * Sliding Window Maximum
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * <p>
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 */
public class S239_SlidingWindowMaximum {

  /**
   * 使用双端队列
   *
   * @param nums
   * @param k
   * @return
   */
  public int[] maxSlidingWindow(int[] nums, int k) {

    if (nums == null || nums.length == 0) {
      return new int[0];
    }

    int[] result = new int[nums.length - k + 1];

    //声明双端队列
    ArrayDeque<Integer> dequeue = new ArrayDeque<>();

    for (int i = 0; i < nums.length; i++) {

      int item = nums[i];

      //处理前 k - 1 个元素
      if (i < k - 1) {
        dequeue.addLast(item);
        continue;
      }

      //由第 k 个元素开始计算
      dequeue.addLast(item);

      //维护双端队列长度不大于 k
      while (dequeue.size() > k) {
        dequeue.removeFirst();
      }

      //最后的数是最大数，会使后面的失效
      while (dequeue.peekLast() > dequeue.peekFirst() && dequeue.size() != 1) {
        dequeue.removeFirst();
      }

      int maxItem = Integer.MIN_VALUE;

      //找出双端队列的最大值
      for (int temp : dequeue) {
        if (temp > maxItem) {
          maxItem = temp;
        }
      }

      result[i - k + 1] = maxItem;
    }

    return result;
  }


  public int[] maxSlidingWindow_Array(int[] nums, int k){

    if (nums == null || nums.length == 0) {
      return new int[0];
    }

    int[] result = new int[nums.length - k + 1];

    for (int i = 0; i < nums.length - k + 1; i++) {

      int maxValue = Integer.MIN_VALUE;

      for (int j = i; j < i + k; j++) {
        if (nums[j] > maxValue) {
          maxValue = nums[j];
        }
      }

      result[i] = maxValue;
    }

    return result;
  }

  public static void main(String[] args) {

    int[] arrays = {1, 3, -1, -3, 5, 3, 6, 7};

    final S239_SlidingWindowMaximum slidingWindowMaximum = new S239_SlidingWindowMaximum();

    int[] ints = slidingWindowMaximum.maxSlidingWindow(arrays, 3);
    PrintUtils.printArray(ints);

    ints = slidingWindowMaximum.maxSlidingWindow_Array(arrays, 3);
    PrintUtils.printArray(ints);
  }
}
