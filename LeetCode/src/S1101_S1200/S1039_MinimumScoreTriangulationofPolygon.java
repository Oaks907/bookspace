package S1101_S1200;

import org.junit.Test;

/**
 * Create by haifei on 2/4/2020 12:39 AM.
 */
public class S1039_MinimumScoreTriangulationofPolygon {

    public int minScoreTriangulation(int[] A) {

        int n = A.length;
        int[][] dp = new int[n][n];

        for (int d = 2; d < A.length; d++) {
            for (int i = 0; i + d < n; i++) {
                int j = i + d;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]);
                }
            }
        }
        return dp[0][n - 1];
    }

    @Test
    public void test() {

    }
}
