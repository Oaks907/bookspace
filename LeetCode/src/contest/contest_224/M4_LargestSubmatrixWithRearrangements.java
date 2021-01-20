package contest.contest_224;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 20/1/2021 12:51 PM.
 */
public class M4_LargestSubmatrixWithRearrangements {

    public int largestSubmatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] = matrix[i - 1][j] + 1;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < row; i++) {

            Arrays.sort(matrix[i]);

            for (int j = col - 1; j >= 0; j--) {

                int height = matrix[i][j];
                ans = Math.max(ans, height * (col - j));

                if (matrix[i][j] == 0) {
                    break;
                }
            }
        }

        return ans;
    }

    @Test
    public void test() {

        int[][] ans = {{0, 0, 1}, {1, 1, 1}, {1, 0, 1}};

        int result = largestSubmatrix(ans);

        Assert.assertEquals(4, result);
    }
}
