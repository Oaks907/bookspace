package S101_200;

/*
 * Create by haifei on 25/1/2019 8:15 AM.
 */

import utils.PrintUtils;

public class S123_BestTimetoBuyandSellStockIII {

  public int maxProfit_complexDP(int[] prices, int times) {

    if (null == prices || 0 == prices.length) {
      return 0;
    }

    int[][] dp = new int[times + 1][prices.length];

    for (int k = 1; k <= times; k++) {
      for (int i = 1; i < prices.length; i++) {
        int min = prices[0];
        for (int j = 1; j < i; j++) {
          min = Math.min(min, prices[j] - dp[k - 1][j - 1]);
        }
        dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min);
      }
    }
    PrintUtils.printArray(dp);
    System.out.println("--");

    return dp[times][prices.length - 1];
  }

  public int maxProfit(int[] prices) {
    return maxProfit_complexDP(prices, 2);
  }

  public static void main(String[] args) {
    final S123_BestTimetoBuyandSellStockIII sellStockIII =
      new S123_BestTimetoBuyandSellStockIII();

    int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
    System.out.println(sellStockIII.maxProfit_complexDP(prices, 2));

    prices = new int[]{1, 2, 3, 4, 5};
    System.out.println(sellStockIII.maxProfit_complexDP(prices, 2));

    prices = new int[]{7, 6, 4, 3, 1};
    System.out.println(sellStockIII.maxProfit_complexDP(prices, 2));
  }
}
