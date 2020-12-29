package contest.contest_221;

import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 27/12/2020 11:17 AM.
 */
public class M5_WhereWilltheBallFall {

    public int[] findBall(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int[] ans = new int[col];
        for (int i = 0; i < col; i++) {
            ans[i] = i;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (ans[j] == -1) {
                    continue;
                }

                if (grid[i][ans[j]] == 1 && ans[j] + 1 < col && grid[i][ans[j] + 1] == 1) {
                    // 右移
                    ans[j]++;
                } else if (grid[i][ans[j]] == -1 && ans[j] - 1 > 0 && grid[i][ans[j] - 1] == -1) {
                    ans[j]--;
                } else {
                    ans[j] = -1;
                }
            }
        }

        return ans;
    }

    public int[] findBall_rec(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int[] ans = new int[col];
        for (int j = 0; j < col; j++) {
            ans[j] = recursion(grid, 0, j, row, col);
        }

        return ans;
    }

    public int recursion(int[][] grid, int i, int j, int row, int col) {
        if (i == row) {
            return j;
        }
        // 卡在边缘
        if (j == 0 && grid[i][j] == -1) {
            return -1;
        }
        if (j == col - 1 && grid[i][j] == 1) {
            return -1;
        }
        // 卡在中间
        if (grid[i][j] == 1 && grid[i][j + 1] == -1) {
            return -1;
        }
        if (grid[i][j] == -1 && grid[i][j - 1] == 1) {
            return -1;
        }
        return recursion(grid, i + 1, j + grid[i][j], row, col);
    }

    @Test
    public void test() {
        int[][] grid =
                {{1, 1, 1, -1, -1}, {1, 1, 1, -1, -1}, {-1, -1, -1, 1, 1}, {1, 1, 1, 1, -1}, {-1, -1, -1, -1, -1}};

        int[] result = findBall(grid);
        PrintUtils.printArray(result);
    }
}
