package S1101_S1200;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 25/9/2019 8:35 AM.
 */
public class S1130_MinimumCostTreeFromLeafValues {

    public int mctFromLeafValues_dp(int[] arr) {

        int[][] dp = new int[arr.length][arr.length];
        int[][] max = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            int localMax = 0;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] > localMax) {
                    localMax = arr[j];
                }
                max[i][j] = localMax;
            }
        }

        PrintUtils.printArray(max);

        for (int len = 1; len < arr.length; len++) {
            for (int left = 0; left + len < arr.length; left++) {
                int right = left + len;
                dp[left][right] = Integer.MAX_VALUE;
                if (len == 1) {
                    dp[left][right] = arr[left] * arr[right];
                } else {
                    for (int k = left; k < right; k++) {
                        dp[left][right] = Math.min(dp[left][right],
                                dp[left][k] + dp[k + 1][right] + max[left][k] * max[k + 1][right]);
                    }
                }
            }
            PrintUtils.printArray(dp);
            System.out.println("=---");
        }
        PrintUtils.printArray(dp);
        return dp[0][arr.length - 1];
    }

    public int mctFromLeafValues(int[] arr) {

        Stack<Integer> stack = new Stack<>();
        stack.add(Integer.MAX_VALUE);
        int ans = 0;

        for (int cur : arr) {

            while (stack.peek() <= cur) {
                Integer pop = stack.pop();
                ans += pop * Math.min(cur, stack.peek());
            }

            stack.push(cur);
        }

        while (stack.size() > 2) {
            ans += stack.pop() * stack.peek();
        }

        return ans;
    }



    @Test
    public void test() {

        int[] arr = {6, 2, 4, 2};

//        int result = mctFromLeafValues(arr);

//        Assert.assertEquals(32, result);

        mctFromLeafValues_dp(arr);
    }
}
