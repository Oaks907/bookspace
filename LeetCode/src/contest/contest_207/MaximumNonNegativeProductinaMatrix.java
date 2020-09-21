package contest.contest_207;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 20/9/2020 11:02 AM.
 */
public class MaximumNonNegativeProductinaMatrix {

    int MOD = 1000000000 + 7;
    double ans = -1;

    public int maxProductPath(int[][] grid) {

        helper(grid, 0, 0, grid[0][0]);

        return (int) (ans % MOD);
    }

    private void helper(int[][] grid, int row, int col, double val) {
        int maxRow = grid.length;
        int maxCol = grid[0].length;

        if (row == maxRow - 1 && col == maxCol - 1) {
            ans = Math.max(ans, val);
            return;
        }

        if (val == 0) {
            ans = Math.max(ans, val);
            return;
        }

        if (row >= maxRow || col >= maxCol) {
            return;
        }

        // 去左边
        if (col + 1 < maxCol) {
            helper(grid, row, col + 1, (val * grid[row][col + 1]));
        }
        // 去下面
        if (row + 1 < maxRow) {
            helper(grid, row + 1, col, (val * grid[row + 1][col]));
        }
    }

    public static void dfs(int i, int j, int[][] grid, long sum) {
        int n = grid.length;
        int m = grid[0].length;
        if (i == n - 1 && j == m - 1) {
            //             System.out.println(sum);
            if (sum > max) {
                max = sum;
            }
            return;
        }
        if (sum == 0) {
            if (sum > max) {
                max = sum;
            }
            return;
        }
        if (i >= n || j >= m) {
            return;
        }

        if (i + 1 < n) {
            dfs(i + 1, j, grid, sum * grid[i + 1][j]);
        }
        if (j + 1 < m) {
            dfs(i, j + 1, grid, sum * grid[i][j + 1]);
        }
    }

    @Test
    public void test() {
        int[][] arr = {{-1, -2, -3}, {-2, -3, -3}, {-3, -3, -2}};
        int result = maxProductPath(arr);

        Assert.assertEquals(-1, result);
    }

    @Test
    public void test1() {
        int[][] arr = {{1, -2, 1}, {1, -2, 1}, {3, -4, 1}};
        int result = maxProductPath(arr);

        Assert.assertEquals(8, result);
    }

    @Test
    public void test2() {
        int[][] arr = {{1, 3}, {0, -4}};
        int result = maxProductPath(arr);

        Assert.assertEquals(0, result);
    }

    @Test
    public void test3() {
        int[][] arr = {{-1, -4, 2}, {4, 3, -1}, {2, -4, 4}, {1, -1, -4}};
        int result = maxProductPath(arr);

        Assert.assertEquals(768, result);
    }

    @Test
    public void test4() {

        int[][] arr = {{2, 1, 3, 0, -3, 3, -4, 4, 0, -4}, {-4, -3, 2, 2, 3, -3, 1, -1, 1, -2},
                {-2, 0, -4, 2, 4, -3, -4, -1, 3, 4}, {-1, 0, 1, 0, -3, 3, -2, -3, 1, 0},
                {0, -1, -2, 0, -3, -4, 0, 3, -2, -2}, {-4, -2, 0, -1, 0, -3, 0, 4, 0, -3},
                {-3, -4, 2, 1, 0, -4, 2, -4, -1, -3}, {3, -2, 0, -4, 1, 0, 1, -3, -1, -1},
                {3, -4, 0, 2, 0, -2, 2, -4, -2, 4}, {0, 4, 0, -3, -4, 3, 3, -1, -2, -2}};
        int result = maxProductPath(arr);

        Assert.assertEquals(19215865, result);

        result = maxProductPath_2(arr);

        Assert.assertEquals(19215865, result);
    }

    public static int maxProductPath_2(int[][] grid) {
        max = -1;
        dfs(0, 0, grid, grid[0][0]);
        long aaa = 1000000007;
        return (int) (max % aaa);
    }

    static long max = -1;

}
