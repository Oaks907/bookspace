package bweekly.contest_30;

/**
 * Create by haifei on 11/7/2020 11:53 PM.
 */
public class winnerSquareGame {

    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; i >= j * j; j++) {
                dp[i] = !dp[i - j * j];
                if (dp[i]) {
                    break;
                }
            }
        }

        return dp[n];
    }
}
