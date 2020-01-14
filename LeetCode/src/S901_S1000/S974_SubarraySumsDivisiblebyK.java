package S901_S1000;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/subarray-sums-divisible-by-k/
 * Create by haifei on 13/1/2020 8:33 PM.
 */
public class S974_SubarraySumsDivisiblebyK {

  public int subarraysDivByK_TLE(int[] A, int K) {

    int result = 0;
    for (int i = 0; i < A.length; i++) {
      int sum = A[i];
      if (sum % K == 0) {
        result++;
      }
      for (int j = i + 1; j < A.length; j++) {
        sum += A[j];
        if (sum % K == 0) {
          result++;
        }
      }
    }
    return result;
  }

  public int subarraysDivByK(int[] A, int K) {
    int[] map = new int[K];
    map[0] = 1; // 存在取模为0的直接算一次
    int count = 0, sum = 0;

    for (int a : A) {
      sum = (sum + a) % K;
      if (sum < 0) {sum += K;}   // Because -1 % 5 = -1, but we need the positive mod 4
      count += map[sum];  // 同一前缀，说明可以去除这个sum来构成符合条件的数组
      map[sum]++;
    }
    return count;
  }


  @Test
  public void test() {

    int[] arr = {4, 5, 0, -2, -3, 1};

    int result = subarraysDivByK_TLE(arr, 5);

    Assert.assertEquals(7, result);

    result = subarraysDivByK(arr, 5);

    Assert.assertEquals(7, result);
  }
}
