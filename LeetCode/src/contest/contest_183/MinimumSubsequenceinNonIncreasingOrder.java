package contest.contest_183;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Test;

/**
 * Create by haifei on 6/4/2020 6:45 PM.
 */
public class MinimumSubsequenceinNonIncreasingOrder {

    public List<Integer> minSubsequence(int[] nums) {

        List<Integer> res = new ArrayList<>();
        int totalSum = 0;
        int subSum = 0;

        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            queue.add(num);
            totalSum += num;
        }

        while (subSum <= totalSum / 2) {
            Integer poll = queue.poll();
            System.out.println(poll);
            res.add(poll);
            subSum += poll;
        }

        return res;
    }

    @Test
    public void test() {

        int[] arr = {4, 3, 10, 9, 8};

        List<Integer> res = minSubsequence(arr);

        System.out.println(res);
    }
}
