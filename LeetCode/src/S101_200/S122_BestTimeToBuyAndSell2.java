package S101_200;

/**
 * Create by haifei on 3/2/2018.
 */
public class S122_BestTimeToBuyAndSell2 {

  public int maxProfit(int[] prices) {

    int profile = 0 , index = 0;
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
}
