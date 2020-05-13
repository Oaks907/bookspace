package S701_800;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 13/5/2020 1:11 PM.
 */
public class S764_LargestPlusSign {

    public int orderOfLargestPlusSign_complexDP(int N, int[][] mines) {

        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], 1);
        }
        for (int[] m : mines) {
            dp[m[0]][m[1]] = 0;
        }

        int res = 0;
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            // left
            cnt = 0;
            for (int j = 0; j < N; j++) {
                if (dp[i][j] != 0) {
                    dp[i][j] = ++cnt;
                }
            }
            // right
            cnt = 0;
            for (int j = N - 1; j >= 0; j--) {
                if (dp[i][j] != 0) {
                    dp[i][j] = Math.min(++cnt, dp[i][j]);
                }
            }
        }

        for (int j = 0; j < N; j++) {
            // bottom
            cnt = 0;
            for (int i = 0; i < N; i++) {
                if (dp[i][j] != 0) {
                    dp[i][j] = Math.min(++cnt, dp[i][j]);
                }
            }
            // top
            cnt = 0;
            for (int i = N - 1; i >= 0; i--) {
                if (dp[i][j] != 0) {
                    dp[i][j] = Math.min(++cnt, dp[i][j]);
                }
                res = Math.max(res, dp[i][j]);
            }
        }

        PrintUtils.printArray(dp);

        return res;
    }

    public int orderOfLargestPlusSign(int N, int[][] mines) {

        int[][] grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(grid[i], N);
        }

        for (int[] m : mines) {
            grid[m[0]][m[1]] = 0;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0, k = N - 1, l = 0, r = 0, u = 0, d = 0; j < N; j++, k--) {
                grid[i][j] = Math.min(grid[i][j], l = (grid[i][j] == 0 ? 0 : l + 1));  // left direction
                grid[i][k] = Math.min(grid[i][k], r = (grid[i][k] == 0 ? 0 : r + 1));  // right direction
                grid[j][i] = Math.min(grid[j][i], u = (grid[j][i] == 0 ? 0 : u + 1));  // up direction
                grid[k][i] = Math.min(grid[k][i], d = (grid[k][i] == 0 ? 0 : d + 1));  // down direction
            }
        }

        int res = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                res = Math.max(res, grid[i][j]);
            }
        }

        return res;
    }

    @Test
    public void test() {
        int[][] arr = {{4, 2}};

        int result = orderOfLargestPlusSign_complexDP(5, arr);

        Assert.assertEquals(2, result);
    }
}
