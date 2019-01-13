package S101_200;

/**
 * Create by haifei on 3/2/2018.
 */
public class S122_BestTimeToBuyAndSell2 {

  public int maxProfit(int[] prices) {

    int profile = 0, index = 0;
    while (index < prices.length) {
      while (index + 1 < prices.length && prices[index + 1] <= prices[index]) {
        index++;
      }
      int min = prices[index]; //找到局部最小
      while (index + 1 < prices.length && prices[index + 1] >= prices[index]) {
        index++;
      }
      profile += index < prices.length ? prices[index++] - min : 0;  //判断index是否指向最后一位，并且运算index到下一位
    }

    return profile;
  }


  /**
   * 贪心算法
   *
   * @param prices
   * @return
   */
  public int maxProfile_greedy(int[] prices) {

    int profile = 0;

    for (int i = 0; i < prices.length - 1; i++) {
      if (prices[i] < prices[i + 1]) {
        profile += prices[i + 1] - prices[i];
      }
    }

    return profile;
  }

  public int maxProfile_dp(int[] prices) {

    int[] dp = new int[prices.length];

    for (int i = 1; i < prices.length; i++) {
      if (prices[i] > prices[i - 1]) {
        dp[i] = dp[i - 1] + (prices[i] - prices[i - 1]);
      } else {
        dp[i] = dp[i - 1];
      }
    }

    return dp[prices.length - 1];
  }

  public static void main(String[] args) {


    final S122_BestTimeToBuyAndSell2 sell2 = new S122_BestTimeToBuyAndSell2();

    int[] arr = {7, 1, 5, 3, 6, 4};
    System.out.println(sell2.maxProfile_greedy(arr));

    arr = new int[]{1, 2, 3, 4, 5};
    System.out.println(sell2.maxProfile_greedy(arr));


    arr = new int[]{7, 1, 5, 3, 6, 4};
    System.out.println(sell2.maxProfile_dp(arr));

    arr = new int[]{1, 2, 3, 4, 5};
    System.out.println(sell2.maxProfile_dp(arr));
  }
}