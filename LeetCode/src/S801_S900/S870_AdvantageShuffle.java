package S801_S900;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Test;

import utils.PrintUtils;

/**
 * https://leetcode.com/problems/advantage-shuffle/
 * Create by haifei on 27/2/2020 12:39 AM.
 */
public class S870_AdvantageShuffle {

    public int[] advantageCount(int[] A, int[] B) {

        Arrays.sort(A);
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < B.length; i++) {
            queue.offer(new int[] {B[i], i});
        }

        int[] result = new int[A.length];
        int low = 0;
        int high = A.length - 1;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int val = poll[0];
            int index = poll[1];

            if (A[high] > val) {
                result[index] = A[high--];
            } else {
                result[index] = A[low++];
            }
        }

        return result;
    }

    @Test
    public void test1() {
        int[] a = {2, 7, 11, 15};
        int[] b = {1, 10, 4, 11};

        PrintUtils.printArray(advantageCount(a, b));
    }

    @Test
    public void test2() {
        int[] a = {12, 24, 8, 32};
        int[] b = {13, 25, 32, 11};

        PrintUtils.printArray(advantageCount(a, b));
    }
}
