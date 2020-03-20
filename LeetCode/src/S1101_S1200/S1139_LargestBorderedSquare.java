package S1101_S1200;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 19/3/2020 11:05 PM.
 */
public class S1139_LargestBorderedSquare {

    public int largest1BorderedSquare(int[][] grid) {

        int row = grid.length;
        int col = grid[0].length;

        int[][] left = new int[row][col];
        int[][] top = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] > 0) {
                    left[i][j] = j > 0 ? left[i][j - 1] + 1 : 1;
                    top[i][j] = i > 0 ? top[i - 1][j] + 1 : 1;
                }
            }
        }

        for (int l = Math.min(row, col); l > 0; l--) {
            for (int i = 0; i < row - l + 1; i++) {
                for (int j = 0; j < col - l + 1; j++) {
                    if (top[i + l - 1][j] >= l && top[i + l - 1][j + l - 1] >= l && left[i][j + l - 1] >= l
                                && left[i + l - 1][j + l - 1] >= l) {
                        return l * l;
                    }
                }
            }
        }

        return 0;
    }

    public int largest1BorderedSquare_2(int[][] grid) {

        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[][][] dp = new int[m + 1][n + 1][2];
        int max = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dp[i + 1][j + 1][0] = dp[i][j + 1][0] + 1;  // top to bottom
                    dp[i + 1][j + 1][1] = dp[i + 1][j][1] + 1;  // left to right;

                    int len = Math.min(dp[i + 1][j + 1][0], dp[i + 1][j + 1][1]);

                    for (int k = len; k > max; k--) {
                        int len1 = Math.min(dp[i + 1 - k + 1][j + 1][1], dp[i + 1][j + 1 - k + 1][0]);
                        if (len1 > k) {
                            max = len1;
                        }
                    }

                } else {
                    dp[i + 1][j + 1][0] = 0;
                    dp[i + 1][j + 1][1] = 0;
                }
            }
        }

        return max * max;
    }

    @Test
    public void test() {

        int[][] grid = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};

        int result = largest1BorderedSquare(grid);

        Assert.assertEquals(9, result);
    }

    @Test
    public void test1() {

        int[][] grid = {{1, 1, 0, 0}};

        int result = largest1BorderedSquare(grid);

        Assert.assertEquals(1, result);
    }

}
