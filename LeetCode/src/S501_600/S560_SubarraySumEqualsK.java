package S501_600;

import utils.PrintUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by haifei on 12/12/2018 8:40 AM.
 */
public class S560_SubarraySumEqualsK {

  int count = 0;

  /**
   * 一般解法，采用的方式由第一个位置一直考虑所有的情况
   * 时间复杂度： n * ( n - 1 )
   * 空间复杂度： 1
   *
   * @param nums
   * @param k
   * @return
   */
  public int subarraySum(int[] nums, int k) {
    count = 0;
    recursion(nums, 0, k);
    return count;
  }

  private void recursion(int[] nums, int startIndex, int current) {
    if (startIndex > nums.length - 1) {
      return;
    }

    int temp = current;
    for (int i = startIndex; i < nums.length; i++) {
      temp -= nums[i];
      if (temp == 0) {
        count++;
      }
    }

    recursion(nums, startIndex + 1, current);
  }

  /**
   * 反而更慢了
   * 时间复杂度：2 * n^2
   * 空间复杂度：n
   *
   * @param nums
   * @param k
   * @return
   */
  public int subarraySum_Arr(int[] nums, int k) {

    if (null == nums || 0 == nums.length) {
      return 0;
    }

    //借助dp数组保存数据
    int length = nums.length;
    int[] dp = new int[nums.length];
    dp[0] = nums[0];

    for (int i = 1; i < nums.length; i++) {
      dp[i] = nums[i] + dp[i - 1];
    }

    int count = 0;

    for (int i = 0; i < length; i++) {
      for (int j = i; j < length; j++) {
        if (dp[j] == k) {
          count++;
        }
      }

      dp[i] = 0;

      for (int j = i + 1; j < length; j++) {
        dp[j] = dp[j] - nums[i];
      }
    }

    return count;
  }

  public int subarraySum_HashMap(int[] nums, int k) {

    if (null == nums || 0 == nums.length) {
      return 0;
    }

    int sum = 0;
    int result = 0;

    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);

    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      if (map.containsKey(sum - k)) {
        result += map.get(sum - k);
      }
      map.put(sum, map.getOrDefault(sum, 0) + 1);
    }

    return result;
  }


  public static void main(String[] args) {
    final S560_SubarraySumEqualsK subarraySumEqualsK = new S560_SubarraySumEqualsK();

    int[] arr = new int[]{1, 1, 1, 2, 3, 4, 5, 1, 1};
    System.out.println(subarraySumEqualsK.subarraySum(arr, 3));
    arr = new int[]{1, 1, 1};
    System.out.println(subarraySumEqualsK.subarraySum(arr, 2));

    System.out.println(subarraySumEqualsK.subarraySum_Arr(arr, 2));

    arr = new int[]{1, 1, 1, 2, 3, 4, 5, 1, 1};
    System.out.println(subarraySumEqualsK.subarraySum_Arr(arr, 3));
  }
}
