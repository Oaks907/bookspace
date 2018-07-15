package S101_200;

/**
 * Create by haifei on 29/1/2018.
 */
public class S121_BestTimeToBuyAndSellStock {

  public static int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0 || prices.length == 1) {
      return 0;
    }

    int result = 0;
    int[] temp = new int[prices.length];
    temp[prices.length - 1] = prices[prices.length - 1];

    for (int i = prices.length - 2; i >= 0; i--) {
      int value = prices[i];
      if (value > temp[i + 1]) {
        temp[i] = prices[i];
      } else {
        temp[i] = temp[i + 1];
      }
    }

    for (int i = 0; i < prices.length - 1; i++) {
      int value = temp[i + 1];
      result = Math.max(result, value - prices[i]);
    }

    return result;
  }

  public static void main(String[] args) {
    int[] nums1 = {7, 1, 5, 3, 6, 4}; //5
    int[] nums2 = {1, 2};
    System.out.println(maxProfit(nums1));
    System.out.println(maxProfit(nums2));
  }
}
