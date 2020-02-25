package S201_300;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 * Create by haifei on 25/2/2020 11:48 AM.
 */
public class S209_MinimumSizeSubarraySum {

    public int minSubArrayLen(int s, int[] nums) {

        int result = Integer.MAX_VALUE;
        int sum;
        int count;

        for (int i = 0; i < nums.length; i++) {
            sum = nums[i];
            count = 1;
            if (sum >= s) {
                return count;
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                count++;
                if (sum >= s) {
                    result = Math.min(result, count);
                    sum = 0;
                    count = 0;
                }
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }

    /**
     * GOOD solution
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int s, int[] nums) {

        int result = Integer.MAX_VALUE;
        int sum = 0;
        int i = 0;
        int j = 0;

        while (j < nums.length) {
            sum += nums[j++];
            while (sum >= s) {
                result = Math.min(result, j - i);
                sum -= nums[i++];
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }

    @Test
    public void test() {
        int[] arr = {2, 3, 1, 2, 4, 3};

        int result = minSubArrayLen(7, arr);

        Assert.assertEquals(2, result);
    }

    @Test
    public void test1() {
        int[] arr = {1, 2, 3, 4, 5};

        int result = minSubArrayLen(11, arr);

        Assert.assertEquals(3, result);
    }

    @Test
    public void test2() {
        int[] arr = {10, 2, 3};

        int result = minSubArrayLen(6, arr);

        Assert.assertEquals(1, result);
    }
}
