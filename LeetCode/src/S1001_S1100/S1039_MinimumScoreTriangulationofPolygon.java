package S1001_S1100;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 7/4/2020 11:46 PM.
 */
public class S1039_MinimumScoreTriangulationofPolygon {

    public int minScoreTriangulation(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];
        for (int d = 2; d < n; ++d) {
            for (int i = 0; i + d < n; ++i) {
                int j = i + d;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; ++k) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]);
                }
            }
        }
        return dp[0][n - 1];
    }

    @Test
    public void test() {

        int[] arr = {1, 2, 3};

        int result = minScoreTriangulation(arr);

        Assert.assertEquals(6, result);
    }

    @Test
    public void test1() {

        int[] arr = {3, 7, 4, 5};

        int result = minScoreTriangulation(arr);

        Assert.assertEquals(144, result);
    }

    @Test
    public void test2() {

        int[] arr = {1, 3, 1, 4, 1, 5};

        int result = minScoreTriangulation(arr);

        Assert.assertEquals(13, result);
    }
}
