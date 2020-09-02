package S1201_S1300;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 13/5/2020 6:20 PM.
 */
public class S1262_GreatestSumDivisiblebyThree {

    /*
     * 17 ms beats 36%
     * 递归解法
     */
    public int maxSumDivThree_recursion(int[] nums) {
        Arrays.sort(nums);

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        recursion(nums, sum, 0);

        return ans;
    }

    int ans = 0;

    private void recursion(int[] nums, int sum, int start) {
        if (ans >= sum) {
            return;
        }
        if (sum != 0 && sum % 3 == 0) {
            ans = Math.max(sum, ans);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            //            System.out.println("sum=" + sum + ",num=" +val + "," + (sum -val) + ",start:" + i);
            recursion(nums, sum - nums[i], i + 1);
        }
    }

    /**
     * 动态规划解法
     */
    public int maxSumDivThree(int[] nums) {

        int len = nums.length;
        int[][] dp = new int[len + 1][3];
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;
        dp[0][2] = Integer.MIN_VALUE;

        /**
         * 转移的核心思想在于当前值的选择与否
         */
        for (int i = 1; i <= nums.length; i++) {

            int val = nums[i - 1];
            int mod = nums[i - 1] % 3;

            if (mod == 0) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][0] + val);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][1] + val);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][2] + val);
            } else if (mod == 1) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] + val);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + val);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + val);
            } else if (mod == 2) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + val);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] + val);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][0] + val);
            }
        }

//        PrintUtils.printArray(dp);

        return dp[len][0];
    }

    @Test
    public void test() {

        int[] arr = {3, 6, 5, 1, 8};

        int result = maxSumDivThree(arr);

        Assert.assertEquals(18, result);
    }

    @Test
    public void test1() {

        int[] arr = {1, 2, 3, 4, 4};

        int result = maxSumDivThree(arr);

        Assert.assertEquals(12, result);
    }

    @Test
    public void test2() {

        int[] arr =
                {366, 809, 6, 792, 822, 181, 210, 588, 344, 618, 341, 410, 121, 864, 191, 749, 637, 169, 123, 472, 358,
                        908, 235, 914, 322, 946, 738, 754, 908, 272, 267, 326, 587, 267, 803, 281, 586, 707, 94, 627,
                        724, 469, 568, 57, 103, 984, 787, 552, 14, 545, 866, 494, 263, 157, 479, 823, 835, 100, 495,
                        773, 729, 921, 348, 871, 91, 386, 183, 979, 716, 806, 639, 290, 612, 322, 289, 910, 484, 300,
                        195, 546, 499, 213, 8, 623, 490, 473, 603, 721, 793, 418, 551, 331, 598, 670, 960, 483, 154,
                        317, 834, 352};

        int result = maxSumDivThree(arr);

        Assert.assertEquals(50487, result);
    }
}
