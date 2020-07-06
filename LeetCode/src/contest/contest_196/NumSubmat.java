package contest.contest_196;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 5/7/2020 10:41 AM.
 */
public class NumSubmat {

    public int numSubmat(int[][] mat) {

        int row = mat.length;
        int col = mat[0].length;

        int[][] count = new int[row][col];

        int cnt;
        for (int i = 0; i < row; i++) {
            cnt = 0;
            for (int j = 0; j < col; j++) {
                if (mat[i][j] == 1) {
                    cnt++;
                } else {
                    cnt = 0;
                }
                count[i][j] = cnt;
            }
        }

        int result = 0;
        int minVal;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                minVal = Integer.MAX_VALUE;
                for (int k = i; k >= 0; k--) {
                    minVal = Math.min(minVal, count[k][j]);
                    result += minVal;
                }
            }
        }

        return result;
    }

    @Test
    public void test() {

        int[][] arr = {{1, 0, 1}, {1, 1, 0}, {1, 1, 0}};

        int result = numSubmat(arr);

        Assert.assertEquals(13, result);
    }

    @Test
    public void test1() {

        int[][] arr = {{0, 1, 1, 0}, {0, 1, 1, 1}, {1, 1, 1, 0}};

        int result = numSubmat(arr);

        Assert.assertEquals(24, result);
    }

    @Test
    public void test2() {

        int[][] arr = {{1, 1, 1, 1, 1, 1}};

        int result = numSubmat(arr);

        Assert.assertEquals(21, result);
    }

    @Test
    public void test3() {

        int[][] arr = {{1, 0, 1}, {0, 1, 0}, {1, 0, 1}};

        int result = numSubmat(arr);

        Assert.assertEquals(5, result);
    }
}
