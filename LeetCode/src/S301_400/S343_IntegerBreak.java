package S301_400;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * https://leetcode.com/problems/integer-break/
 * Create by haifei on 14/3/2020 2:21 PM.
 */
public class S343_IntegerBreak {

    public int integerBreak(int n) {
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        nums[2] = 1;

        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                int val = Math.max(nums[j] * nums[i - j], j * nums[i - j]);
                val = Math.max(val, nums[j] * (i - j));
                val = Math.max(val, j * (i - j));

                if (nums[i] < val) {
                    nums[i] = val;
                }
            }
        }

//        PrintUtils.printArray(nums);

        return nums[n];
    }

    @Test
    public void test() {

        int result = integerBreak(10);

        Assert.assertEquals(36, result);
    }
}
