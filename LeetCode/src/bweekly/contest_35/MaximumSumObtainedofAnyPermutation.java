package bweekly.contest_35;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 19/9/2020 11:24 PM.
 */
public class MaximumSumObtainedofAnyPermutation {

    int ans = 0;
    boolean[] used;

    int[] count;

    public int maxSumRangeQuery_(int[] nums, int[][] requests) {

        int len = nums.length;
        used = new boolean[len];
        count = new int[len];

        for (int i = 0; i < requests.length; i++) {
            int left = requests[i][0];
            int right = requests[i][1];
            while (left <= right) {
                count[left++]++;
            }
        }

        helper(nums, 0, new ArrayList<>());

        return ans;
    }

    public void helper(int[] nums, int index, List<Integer> list) {

        if (index >= nums.length) {
            int sum = 0;
            for (int i = 0; i < count.length; i++) {
                if (count[i] == 0) {
                    continue;
                }
                sum = (sum + (list.get(i) * count[i]) % MOD) % MOD;
            }
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            list.add(nums[i]);
            used[i] = true;

            helper(nums, index + 1, list);

            used[i] = false;
            list.remove(list.size() - 1);
        }
    }

    int MOD = 1000000000 + 7;
    public int maxSumRangeQuery(int[] nums, int[][] requests) {

        int[] count = new int[nums.length];

        int len = nums.length;

        int[] delta = new int[len + 1];
        for (int[] r : requests) {
            delta[r[0]]++;
            delta[r[1] + 1]--;
        }

        for (int i = 0; i < len; i++) {
            count[i] = (i > 0 ? count[i - 1] : 0) + delta[i];
        }

        Arrays.sort(count);
        Arrays.sort(nums);

        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans = (ans + (nums[i] * count[i]) % MOD) % MOD;
        }

        return ans;
    }

    @Test
    public void test() {

        int[] arr = {1, 2, 3, 4, 5};

        int[][] re = {{1, 3}, {0, 1}};

        int result = maxSumRangeQuery(arr, re);

        Assert.assertEquals(19, result);
    }

}
