package S1201_S1300;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * https://leetcode.com/problems/get-equal-substrings-within-budget/
 * Create by haifei on 25/2/2020 10:23 PM.
 */
public class S1208_GetEqualSubstringsWithinBudget {

    public int equalSubstring2(String s, String t, int maxCost) {

        char[] sC = s.toCharArray();
        char[] tC = t.toCharArray();
        int[] nums = new int[sC.length];

        for (int i = 0; i < sC.length; i++) {
            nums[i] = Math.abs(sC[i] - tC[i]);
        }

        PrintUtils.printArray(nums);

        int i = 0;
        int j = 0;
        int result = 0;
        int cost = 0;

        while (j < nums.length) {
            cost += nums[j];

            while (i <= j && cost > maxCost) {
                cost -= nums[i++];
            }

            result = Math.max(j - i + 1, result);
            j++;
        }

        return result;
    }

    public int equalSubstring(String s, String t, int k) {
        int n = s.length(), i = 0, j;
        for (j = 0; j < n; ++j) {
            k -= Math.abs(s.charAt(j) - t.charAt(j));
            if (k < 0) {
                k += Math.abs(s.charAt(i) - t.charAt(i));
                ++i;
            }
        }

        return j - i;
    }

    @Test
    public void test3() {

        int result = equalSubstring("krrgw", "zjxss", 19);

        Assert.assertEquals(2, result);
    }

    @Test
    public void test2() {

        int result = equalSubstring("abcd", "acde", 0);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test1() {

        int result = equalSubstring("abcd", "cdef", 3);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test() {

        int result = equalSubstring("abcd", "bcdf", 3);

        Assert.assertEquals(3, result);
    }
}
