package bweekly.contest_30;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 11/7/2020 10:41 PM.
 */
public class RangeNum {

    int MOD = 1000000000 + 7;

    List<Integer> numList = new ArrayList<>();

    public int rangeSum(int[] nums, int n, int left, int right) {

        for (int i = 0; i < nums.length; i++) {
            recursion(nums, i, nums[i]);
        }

        Collections.sort(numList);

        int result = 0;
        for (int i = left - 1; i < right && i < numList.size(); i++) {
            result = (result + numList.get(i)) % MOD;
        }

        return result;
    }

    public void recursion(int[] nums, int curIndex, int sum) {

        numList.add(sum);

        if (curIndex + 1 >= nums.length) {
            return;
        }

        recursion(nums, curIndex + 1, sum + nums[curIndex + 1]);
    }

    @Test
    public void test() {

        int[] nums = {1, 2, 3, 4};

        int result = rangeSum(nums, 4, 1, 5);

        Assert.assertEquals(13, result);
    }

    @Test
    public void test1() {

        int[] nums = {1, 2, 3, 4};

        int result = rangeSum(nums, 4, 3, 4);

        Assert.assertEquals(6, result);
    }

    @Test
    public void test2() {

        int[] nums = {1, 2, 3, 4};

        int result = rangeSum(nums, 4, 1, 10);

        Assert.assertEquals(50, result);
    }
}
