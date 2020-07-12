package bweekly.contest_30;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 11/7/2020 10:42 PM.
 */
public class MinDifference {

    public int minDifference(int[] nums) {

        Arrays.sort(nums);
        int count = 3;
        int result = Integer.MAX_VALUE;

        if (nums.length <= 4) {
            return 0;
        }

        result = Math.min(nums[nums.length - 1 - 3] - nums[0], nums[nums.length - 1] - nums[3]);

        for (int i = 0; i < count; i++) {
            int lowIndex = i + 1;
            int highIndex = nums.length - 1 + (i - 2);

            result = Math.min(result, nums[highIndex] - nums[lowIndex]);
        }

        return result;
    }

    @Test
    public void test() {
        int[] nums = {5, 3, 2, 4};

        int result = minDifference(nums);

        Assert.assertEquals(0, result);
    }

    @Test
    public void test2() {
        int[] nums = {1, 5, 0, 10, 14};

        int result = minDifference(nums);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test3() {
        int[] nums = {6, 6, 0, 1, 1, 4, 6};

        int result = minDifference(nums);

        Assert.assertEquals(2, result);
    }

    @Test
    public void test4() {
        int[] nums = {1, 5, 6, 14, 15};

        int result = minDifference(nums);

        Assert.assertEquals(1, result);
    }
}
