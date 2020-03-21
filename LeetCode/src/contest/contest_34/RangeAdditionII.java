package contest.contest_34;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import sun.applet.Main;

/**
 * Create by haifei on 21/3/2020 7:09 PM.
 */
public class RangeAdditionII {

    /**
     * Memory Limit Exceeded
     */
    public int maxCount_MLE(int m, int n, int[][] ops) {

        int[][] arr = new int[m][n];
        int maxVal = Integer.MIN_VALUE;
        int result = m * n;

        for (int i = 0; i < ops.length; i++) {
            int row = ops[i][0];
            int col = ops[i][1];

            for (int j = 0; j < row; j++) {
                for (int k = 0; k < col; k++) {
                    arr[j][k] += 1;
                    if (arr[j][k] > maxVal) {
                        maxVal = arr[j][k];
                        result = 1;
                    } else if (arr[j][k] == maxVal) {
                        result++;
                    }
                }
            }
        }

        return result;
    }

    public int maxCount(int m, int n, int[][] ops) {

        int minRow = m;
        int minCol = n;

        for (int i = 0; i < ops.length; i++) {
            int row = ops[i][0];
            int col = ops[i][1];

            minRow = Math.min(row, minRow);
            minCol = Math.min(col, minCol);
        }

        return minRow * minCol;
    }

    @Test
    public void test() {

        int[][] arr = {{2, 2}, {3, 3}};

        int result = maxCount(3, 3, arr);

        Assert.assertEquals(4, result);
    }
}
