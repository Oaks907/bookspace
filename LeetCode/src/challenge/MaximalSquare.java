package challenge;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 27/4/2020 6:29 PM.
 */
public class MaximalSquare {

    public int maximalSquare_self(char[][] matrix) {
        if (null == matrix || 0 == matrix.length) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        int[][] dp = new int[row][col];
        int res = 0;

        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == '0') {
                dp[i][0] = 0;
            } else {
                dp[i][0] = 1;
            }
            res = Math.max(dp[i][0], res);
        }

        for (int i = 0; i < col; i++) {
            if (matrix[0][i] == '0') {
                dp[0][i] = 0;
            } else {
                dp[0][i] = 1;
            }
            res = Math.max(dp[0][i], res);
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                    continue;
                }
                if (matrix[i - 1][j] == '0' || matrix[i][j - 1] == '0') {
                    dp[i][j] = 1;
                    res = Math.max(dp[i][j], res);
                    continue;
                }

                int len = (int) Math.sqrt(Math.min(dp[i - 1][j], dp[i][j - 1]));
                while (len > 0) {
                    if (dp[i - len][j - len] != 0) {
                        dp[i][j] = (len + 1) * (len + 1);
                        break;
                    } else {
                        len--;
                        if (len == 0) {
                            dp[i][j] = matrix[i][j] == '0' ? 0 : 1;
                        }
                    }
                }

                res = Math.max(dp[i][j], res);
            }
        }

        //        PrintUtils.printArray(dp);

        return res;
    }

    public int maximalSquare(char[][] matrix) {

        if (null == matrix || 0 == matrix.length) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        int[][] dp = new int[row + 1][col + 1];
        int res = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res * res;
    }

    @Test
    public void test1() {

        char[][] matrix = {{'0', '1', '1', '0', '1'}, {'1', '1', '0', '1', '0'}, {'0', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '0'}, {'1', '1', '1', '1', '1'}, {'0', '0', '0', '0', '0'}};

        int result = maximalSquare(matrix);

        Assert.assertEquals(9, result);
    }

    @Test
    public void test() {

        char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};

        int result = maximalSquare(matrix);

        Assert.assertEquals(4, result);
    }
}
