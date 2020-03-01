package S1301_S1400;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/
 * Create by haifei on 1/3/2020 8:54 PM.
 */
public class S1368_NOT_MinimumCosttoMakeatLeastOneValidPathinaGrid {

    /**
     * 存在测试用例通不过
     *
     * @param grid
     * @return
     */
    public int minCost_can_not_wort(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int left = 2, right = 1, top = 4, bottom = 3;

        int[][] dp = new int[m][n];

        for (int i = 1; i < m; i++) {
            if (grid[i - 1][0] != bottom) {
                dp[i][0] = dp[i - 1][0] + 1;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }

        for (int i = 1; i < n; i++) {
            if (grid[0][i - 1] != right) {
                dp[0][i] += dp[0][i - 1] + 1;
            } else {
                dp[0][i] = dp[0][i - 1];
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int topToBot = 0, leftToRight = 0;
                if (grid[i - 1][j] == bottom) {
                    topToBot = dp[i - 1][j];
                } else {
                    topToBot = dp[i - 1][j] + 1;
                }
                if (grid[i][j - 1] == right) {
                    leftToRight = dp[i][j - 1];
                } else {
                    leftToRight = dp[i][j - 1] + 1;
                }
                dp[i][j] = Math.min(topToBot, leftToRight);
            }
        }

        PrintUtils.printArray(dp);

        return dp[m - 1][n - 1];
    }

    public int minCost(int[][] grid) {
        return 0;
    }

    @Test
    public void test6() {
        int[][] arr =
                {{3, 4, 3}, {2, 2, 2}, {2, 1, 1}, {4, 3, 2}, {2, 1, 4}, {2, 4, 1}, {3, 3, 3}, {1, 4, 2}, {2, 2, 1},
                        {2, 1, 1}, {3, 3, 1}, {4, 1, 4}, {2, 1, 4}, {3, 2, 2}, {3, 3, 1}, {4, 4, 1}, {1, 2, 2},
                        {1, 1, 1}, {1, 3, 4}, {1, 2, 1}, {2, 2, 4}, {2, 1, 3}, {1, 2, 1}, {4, 3, 2}, {3, 3, 4},
                        {2, 2, 1}, {3, 4, 3}, {4, 2, 3}, {4, 4, 4}};

        int result = minCost(arr);

        Assert.assertEquals(18, result);
    }

    @Test
    public void test5() {

        int[][] arr = {{1, 1, 3}, {2, 2, 2}, {4, 4, 1}};

        int result = minCost(arr);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test() {

        int[][] arr = {{1, 1, 1, 1}, {2, 2, 2, 2}, {1, 1, 1, 1}, {2, 2, 2, 2}};

        int result = minCost(arr);

        Assert.assertEquals(3, result);
    }

    @Test
    public void test1() {

        int[][] arr = {{1, 1, 3}, {3, 2, 2}, {1, 1, 4}};

        int result = minCost(arr);

        Assert.assertEquals(0, result);
    }

    @Test
    public void test2() {

        int[][] arr = {{1, 2}, {4, 3}};

        int result = minCost(arr);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test3() {

        int[][] arr = {{2, 2, 2}, {2, 2, 2}};

        int result = minCost(arr);

        Assert.assertEquals(3, result);
    }

    @Test
    public void test4() {

        int[][] arr = {{4}};

        int result = minCost(arr);

        Assert.assertEquals(0, result);
    }
}
