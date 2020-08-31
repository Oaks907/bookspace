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

    public double knightProbability_dp(int N, int K, int r, int c) {
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

    public double knightProbability(int N, int K, int r, int c) {

        int[][] dirs = new int[][] {{-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}};
        double result = 0;
        double[][] count = new double[N][N];
        count[r][c] = 1;

        for (int step = 0; step < K; step++) {
            double[][] temp = new double[N][N];
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    for (int[] dir : dirs) {
                        int x = dir[0] + row;
                        int y = dir[1] + col;

                        if (x < 0 || x >= N || y < 0 || y >= N) {

                        } else {
                            temp[x][y] = temp[x][y] + 0.125 * count[row][col];
                        }
                    }
                }
            }
//            PrintUtils.printArray(temp);
            count = temp;
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                result += count[row][col];
            }
        }

        return result;
    }

    @Test
    public void test() {

        double result = knightProbability(3, 2, 0, 0);

        System.out.println(result);

        Assert.assertTrue(0.0625 == result);
    }
}
