package S301_400;

import utils.PrintUtils;

/**
 * Create by haifei on 24/1/2019 11:51 AM.
 * https://leetcode.com/problems/coin-change/
 */
public class S322_CoinChange {

  public int coinChange(int[] coins, int amount) {

    if (0 == amount) {
      return 0;
    }

    int dp[] = new int[amount + 1];

    for(int i = 1; i < amount + 1; i++){
      dp[i] = -1;
    }

    for (int i = 1; i <= amount; i++) {
      for (int j = coins.length - 1; j >= 0; j--) {
        //保证之前的值存在
        if (i - coins[j] >= 0 && dp[i - coins[j]] != -1) {   //reachable
          if(dp[i] == -1 || dp[i - coins[j]] + 1 < dp[i]){  //update dp
            dp[i] = dp[i - coins[j]] + 1;
          }
        }
      }
    }

    PrintUtils.printArray(dp);

    return dp[amount];
  }

  public static void main(String[] args) {
    final S322_CoinChange coinChange = new S322_CoinChange();
    int[] coins = {0, 3, 5};

    coinChange.coinChange(coins, 11);
  }
}
