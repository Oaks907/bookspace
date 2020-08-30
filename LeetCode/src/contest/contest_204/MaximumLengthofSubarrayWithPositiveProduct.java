package contest.contest_204;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 30/8/2020 11:06 AM.
 */
public class MaximumLengthofSubarrayWithPositiveProduct {

    /**
     * 时间超限
     *
     * @param nums
     * @return
     */
    public int getMaxLen_ELT(int[] nums) {

        int len = nums.length;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                continue;
            }
            int val = nums[i] > 0 ? 1 : -1;
            if (val > 0) {
                ans = Math.max(1, ans);
            }

            for (int j = i + 1; j < len; j++) {
                if (nums[j] == 0) {
                    break;
                }
                val *= nums[j] > 0 ? 1 : -1;
                if (val > 0) {
                    ans = Math.max(ans, j - i + 1);
                }
            }

        }

        return ans;
    }

    public int getMaxLen(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len + 1][2];
        int ans = 0;

        for (int i = 1; i <= len; i++) {
            int val = nums[i - 1];

            if (val == 0) {
                dp[i][0] = 0;
                dp[i][1] = 0;
            } else if (val > 0) {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = dp[i - 1][1] > 0 ? (dp[i - 1][1] + 1) : 0;
            } else {
                dp[i][0] = dp[i - 1][1] > 0 ? (dp[i - 1][1] + 1) : 0;
                dp[i][1] = dp[i - 1][0] + 1;
            }

            ans = Math.max(ans, dp[i][0]);
        }

        return ans;
    }

    @Test
    public void test7() {
        int[] arr = {5, -20, -20, -39, -5, 0, 0, 0, 36, -32, 0, -7, -10, -7, 21, 20, -12, -34, 26, 2};

        int result = getMaxLen(arr);

        Assert.assertEquals(result, 8);
    }

    @Test
    public void test6() {
        int[] arr = {0, 1, -2, -3, -4};

        int result = getMaxLen(arr);

        Assert.assertEquals(result, 3);
    }

    @Test
    public void test1() {
        int[] arr = {1, -2, -3, 4};

        int result = getMaxLen(arr);

        Assert.assertEquals(result, 4);
    }

    @Test
    public void test2() {
        int[] arr = {0, 1, -2, -3, -4};

        int result = getMaxLen(arr);

        Assert.assertEquals(result, 3);
    }

    @Test
    public void test3() {
        int[] arr = {-1, -2, -3, 0, 1};

        int result = getMaxLen(arr);

        Assert.assertEquals(result, 2);
    }

    @Test
    public void test4() {
        int[] arr = {-1, 2};

        int result = getMaxLen(arr);

        Assert.assertEquals(result, 1);
    }

    @Test
    public void test5() {
        int[] arr = {1, 2, 3, 5, -6, 4, 0, 10};

        int result = getMaxLen(arr);

        Assert.assertEquals(result, 4);
    }
}
