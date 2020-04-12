package S301_400;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 12/4/2020 10:22 PM.
 */
public class S309_BestTimetoBuyandSellStockwithCooldown {

    public int maxProfit(int[] prices) {

        if (prices == null || prices.length == 0) return 0;

        int len = prices.length;
        int[] sell = new int[len];
        int[] buy = new int[len];
        buy[0] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            sell[i] = Math.max(buy[i - 1] + prices[i], sell[i - 1]);
            buy[i] = Math.max((i > 1 ? sell[i - 2] : 0) - prices[i], buy[i - 1]);
        }

        return sell[len - 1];
    }

    @Test
    public void test() {
        int[] prices = {1, 2, 3, 0, 2};

        int result = maxProfit(prices);

        Assert.assertEquals(3, result);
    }

    @Test
    public void test2() {
        int[] prices = {1, 2, 3, 0, 2};

        int result = maxProfit(prices);

        Assert.assertEquals(3, result);
    }
}
