package S701_800;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 27/12/2019 12:49 PM.
 * At day i, we may buy stock (from previous sell status) or do nothing (from previous buy status):
 * buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
 * Or
 * At day i, we may sell stock (from previous buy status) or keep holding (from previous sell
 * status):
 * sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
 */
public class S714_BestTimetoBuyandSellStockwithTransactionFee {

  public int maxProfit(int[] prices, int fee) {

    int len = prices.length;
    int buyer[] = new int[len];
    int seller[] = new int[len];

    buyer[0] = -prices[0] - fee;

    for (int i = 1; i < len; i++) {
      buyer[i] = Math.max(buyer[i - 1], seller[i - 1] - prices[i] - fee);
      seller[i] = Math.max(seller[i - 1], buyer[i - 1] + prices[i]);
    }

    return seller[len - 1];
  }

  @Test
  public void test() {
    int[] arr = {1, 3, 2, 8, 4, 9};

    int result = maxProfit(arr, 2);

    Assert.assertEquals(8, result);
  }
}
