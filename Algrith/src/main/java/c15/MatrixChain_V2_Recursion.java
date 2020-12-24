package c15;

import java.util.Arrays;
import java.util.jar.JarEntry;

import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 7/12/2020 6:36 PM.
 */
public class MatrixChain_V2_Recursion {

    public int matrixChainOrder(int[] p) {
        int[][] dp = new int[p.length ][p.length];
        int result = recursion(p, 1, p.length - 1, dp);
        PrintUtils.printArray(dp);
        return result;
    }

    private int recursion(int[] p, int left, int right, int[][] dp) {
        if (left == right) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int i = left; i < right; i++) {
            int recursion =
                    recursion(p, left, i, dp) +recursion(p, i + 1, right, dp) +  p[left - 1] * p[i] * p[right];
            min = Math.min(min, recursion);
        }
        dp[left][right] = min;


        return dp[left][right];
    }

    /**
     * 0, 0, 0, 0, 0, 0, 0,
     * 0, 0, 15750, 7875, 9375, 11875, 15125,
     * 0, 0, 0, 2625, 4375, 7125, 10500,
     * 0, 0, 0, 0, 750, 2500, 5375,
     * 0, 0, 0, 0, 0, 1000, 3500,
     * 0, 0, 0, 0, 0, 0, 5000,
     * 0, 0, 0, 0, 0, 0, 0,
     */
    @Test
    public void test() {
        int[] p = {30, 35, 15, 5, 10, 20, 25};
        int result = matrixChainOrder(p);
    }
}
