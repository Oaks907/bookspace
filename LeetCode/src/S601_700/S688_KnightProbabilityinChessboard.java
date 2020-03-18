package S601_700;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 19/3/2020 1:20 AM.
 */
public class S688_KnightProbabilityinChessboard {

    private int[][] dir = new int[][] {{-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}};
    private double[][][] dp;

    public double knightProbability(int N, int K, int r, int c) {
        dp = new double[N][N][K + 1];

        double result = find(N, K, r, c);

        return result;
    }

    public double find(int N, int K, int r, int c) {
        if (r < 0 || r > N - 1 || c < 0 || c > N - 1) {
            return 0;
        }
        if (K == 0) {
            return 1;
        }

        if (dp[r][c][K] != 0) {
            return dp[r][c][K];
        }

        double rate = 0;

        for (int i = 0; i < dir.length; i++) {
            rate += 0.125 * find(N, K - 1, r + dir[i][0], c + dir[i][1]);
        }
        dp[r][c][K] = rate;
        return rate;
    }

    @Test
    public void test() {

        double result = knightProbability(3, 2, 0, 0);

        System.out.println(result);
    }
}
