package bweekly.contest_35;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 19/9/2020 10:48 PM.
 */
public class MakeSumDivisiblebyP {

    public int minSubarray(int[] nums, int p) {

        long sum = 0;

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            nums[i] %= p;
            sum += nums[i];
        }

        long r = sum % p;
        if (r == 0) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;

        // 求前缀和
        long[] prefixSum = new long[len + 1];
        for (int i = 1; i <= len; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        // 哈希表保存上个前缀和的位置，前缀和需要除以p取余数
        Map<Long, Integer> prefixSumMap = new HashMap<>();

        for (int i = 0; i <= len; i++) {

            Integer index = prefixSumMap.get((prefixSum[i] - r) % p);
            if (index != null) {
                ans = Math.min(ans, i - index);
            }
            prefixSumMap.merge(prefixSum[i] % p, i, Math::max);
        }

        if (ans == Integer.MAX_VALUE || ans == len) {
            return -1;
        }

        return ans;
    }

    @Test
    public void test5() {
        int[] arr = {1000000000, 1000000000, 1000000000};

        int result = minSubarray(arr, 3);

        Assert.assertEquals(0, result);
    }

    @Test
    public void test1() {
        int[] arr = {3, 1, 4, 2};

        int result = minSubarray(arr, 6);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test2() {
        int[] arr = {6, 3, 5, 2};

        int result = minSubarray(arr, 9);

        Assert.assertEquals(2, result);
    }

    @Test
    public void test3() {
        int[] arr = {1, 2, 3};

        int result = minSubarray(arr, 3);

        Assert.assertEquals(0, result);
    }

    @Test
    public void test4() {
        int[] arr = {1, 2, 3};

        int result = minSubarray(arr, 7);

        Assert.assertEquals(-1, result);
    }
}
