package S1301_S1400;

import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 7/3/2020 7:41 PM.
 */
public class S1314_MatrixBlockSum {

    public int[][] matrixBlockSum(int[][] mat, int K) {

        int row = mat.length;
        int col = mat[0].length;

        int[][] sum = new int[row + 1][col + 1];

        for (int i = 1; i < row + 1; i++) {
            for (int j = 1; j < col + 1; j++) {
                sum[i][j] = mat[i - 1][j - 1] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
            }
        }
        
        int[][] result = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int r1 = Math.max(0, i - K);
                int c1 = Math.max(0, j - K);
                int r2 = Math.min(row - 1, i + K);
                int c2 = Math.min(col - 1, j + K);

                r1++;
                c1++;
                r2++;
                c2++;

                result[i][j] = sum[r2][c2] - sum[r2][c1 - 1] - sum[r1 - 1][c2] + sum[r1 - 1][c1 - 1];
            }
        }

        return result;
    }

    @Test
    public void test() {

        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        int[][] result = matrixBlockSum(mat, 1);

        PrintUtils.printArray(result);
    }

    @Test
    public void test2() {

        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        int[][] result = matrixBlockSum(mat, 2);

        PrintUtils.printArray(result);
    }
}
