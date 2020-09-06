package bweekly.contest_34;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 5/9/2020 10:40 PM.
 */
public class MatrixDiagonalSum {

    public int diagonalSum(int[][] mat) {
        int ans = 0;

        int row = mat.length;
        int col = mat[0].length;

        int left = 0;
        int right = col - 1;

        for (int i = 0; i < row; i++) {
            if (left != right) {
                ans += mat[i][left] + mat[i][right];
            } else {
                ans += mat[i][left];
            }
            left++;
            right--;
        }

        return ans;
    }

    @Test
    public void test() {
        int[][] arr = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};

        int res = diagonalSum(arr);

        Assert.assertEquals(8, res);
    }
}
