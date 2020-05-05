package contest.contest_187;

import java.util.Deque;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 3/5/2020 11:06 AM.
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanorEqualtoLimit {

    /**
     * TLE
     */
    public int longestSubarray_TLE(int[] nums, int limit) {

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            Integer helper = helper(nums, i, nums[i], nums[i], 0, limit);
            res = Math.max(res, helper);
        }

        return res;
    }

    private Integer helper(int[] nums, int index, int max, int min, int len, int limit) {
        if (index >= nums.length) {
            return len;
        }

        int val = nums[index];

        if (Math.abs(val - min) > limit || Math.abs(val - max) > limit) {
            return len;
        }

        return helper(nums, index + 1, Math.max(val, max), Math.min(val, min), len + 1, limit);
    }

    /**
     * TLE
     */
    public int longestSubarray2(int[] nums, int limit) {

        int res = 0;
        int len = nums.length;
        int min, max;

        for (int i = 0; i < len; i++) {
            max = nums[i];
            min = nums[i];
            int curLen = 1;
            for (int j = i + 1; j < len; j++) {
                int val = nums[j];
                max = Math.max(max, val);
                min = Math.min(min, val);
                if (max - min <= limit) {
                    curLen++;
                } else {
                    break;
                }
            }

            res = Math.max(curLen, res);
        }

        return res;
    }

    public int longestSubarray(int[] nums, int limit) {

        Deque<Integer> maxDeque = new LinkedList<>();
        Deque<Integer> minDeque = new LinkedList<>();
        int res = 1;
        int l = 0;

        for (int r = 0; r < nums.length; r++) {

            while (!maxDeque.isEmpty() && maxDeque.peekLast() < nums[r]) {
                maxDeque.removeLast();
            }
            maxDeque.addLast(nums[r]);

            while (!minDeque.isEmpty() && minDeque.peekLast() > nums[r]) {
                minDeque.removeLast();
            }
            minDeque.addLast(nums[r]);

            while (Math.abs(maxDeque.peekFirst() - minDeque.peekFirst()) > limit) {
                if (maxDeque.peekFirst() == nums[l]) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() == nums[l]) {
                    minDeque.pollFirst();
                }
                ++l;
            }

            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    @Test
    public void test() {

        int[] nums = {8, 2, 4, 7};

        int result = longestSubarray(nums, 4);

        Assert.assertEquals(2, result);
    }

    @Test
    public void test1() {

        int[] nums = {10, 1, 2, 4, 7, 2};

        int result = longestSubarray(nums, 5);

        Assert.assertEquals(4, result);
    }

    @Test
    public void test2() {

        int[] nums = {4, 2, 2, 2, 4, 4, 2, 2};

        int result = longestSubarray(nums, 0);

        Assert.assertEquals(3, result);
    }
}
