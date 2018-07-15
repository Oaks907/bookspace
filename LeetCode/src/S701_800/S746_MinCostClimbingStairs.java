package S701_800;

import java.util.Map;

/**
 * Create by haifei on 4/1/2018.
 */
public class S746_MinCostClimbingStairs {

  public static void main(String[] args) {

    int nums1[] = {10, 15, 20};
    int nums2[] = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
    System.out.println(minCostClimbingStairs(nums1));
    System.out.println(minCostClimbingStairs(nums2));
    System.out.println(minCostClimbingStairs1(nums1));

  }

  public static int minCostClimbingStairs(int[] cost) {
    if (cost == null || cost.length == 0 || cost.length == 1) {
      return 0;
    }
    int result[] = new int[cost.length + 1];
    result[0] = cost[0];
    result[1] = cost[1];

    for (int i = 2; i <= cost.length; i++) {
      int value = (i == cost.length) ? 0 : cost[i];
      result[i] = Math.min(result[i - 2] + value, result[i - 1] + value);
    }

    return result[cost.length];
  }

  public static int minCostClimbingStairs1(int[] cost) {
    for (int i = cost.length - 3; i >= 0; i--) {
      cost[i] += Math.min(cost[i + 1], cost[i + 2]);
    }
    return Math.min(cost[0], cost[1]);
  }
}
