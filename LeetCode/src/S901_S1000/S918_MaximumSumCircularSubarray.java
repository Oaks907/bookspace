package S901_S1000;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 19/1/2020 12:51 PM.
 */
public class S918_MaximumSumCircularSubarray {

    public int maxSubarraySumCircular(int[] A) {

        int maxSum = Integer.MIN_VALUE, minSum = Integer.MAX_VALUE;
        int total = 0, curMax = 0, curMin = 0;

        for (int a : A) {

            total += a;

            curMax = Math.max(curMax + a, a);
            maxSum = Math.max(maxSum, curMax);

            curMin = Math.min(curMin + a, a);
            minSum = Math.min(curMin, minSum);
        }

        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
    }

    @Test
    public void test1() {
        int[] arr = {1, -2, 3, -2};

        int result = maxSubarraySumCircular(arr);

        Assert.assertEquals(3, result);
    }

    @Test
    public void test2() {
        int[] arr = {1, -2, 3, -2};

        int result = maxSubarraySumCircular(arr);

        Assert.assertEquals(3, result);
    }
}
