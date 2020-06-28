package bweekly.contest_29;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 27/6/2020 10:45 PM.
 */
public class LongestSubarray {

    public int longestSubarray(int[] nums) {

        int[][] arr = new int[2][nums.length];
        boolean haveZero = false;

        int oneCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                oneCount = 0;
                haveZero = true;
            } else {
                arr[0][i] = ++oneCount;
            }
        }

        oneCount = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                oneCount = 0;
            } else {
                arr[1][i] = ++oneCount;
            }
        }

//        PrintUtils.printArray(arr);

        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (i == 0) {
                    result = Math.max(result, arr[1][i + 1]);
                } else if (i == nums.length - 1) {
                    result = Math.max(result, arr[0][i - 1]);
                } else {
                    result = Math.max(result, arr[1][i + 1] + arr[0][i - 1]);
                }
            }
        }

        if (haveZero) {
            return result;
        } else {
            return nums.length - 1;
        }
    }

    @Test
    public void test1() {
        int[] arr = {1, 1, 0, 1};

        int result = longestSubarray(arr);

        Assert.assertEquals(3, result);
    }

    @Test
    public void test2() {
        int[] arr = {0, 1, 1, 1, 0, 1, 1, 0, 1};

        int result = longestSubarray(arr);

        Assert.assertEquals(5, result);
    }

    @Test
    public void test3() {
        int[] arr = {1, 1, 1};

        int result = longestSubarray(arr);

        Assert.assertEquals(2, result);
    }

    @Test
    public void test4() {
        int[] arr = {1, 1, 0, 0, 1, 1, 1, 0, 1};

        int result = longestSubarray(arr);

        Assert.assertEquals(4, result);
    }

    @Test
    public void test() {
        int[] arr = {0, 0, 0};

        int result = longestSubarray(arr);

        Assert.assertEquals(0, result);
    }
}
