package contest.contest_154;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 15/9/2019 11:02 AM.
 */
public class K_ConcatenationMaximumSum {

  //  Memory Limit Exceeded
  public int kConcatenationMaxSum_MemoryLimit(int[] arr, int k) {
    int[] newArr = new int[arr.length * k];
    for (int i = 0; i < arr.length; i++) {
      newArr[i] = arr[i];
    }
    for (int i = arr.length; i < newArr.length; i++) {
      newArr[i] = newArr[i - arr.length];
    }

    int maxSum = 0;
    int tempSum = 0;
    for (int i = 0; i < newArr.length; i++) {
      tempSum += newArr[i];
      if (tempSum < 0) {
        tempSum = 0;
      }

      if (tempSum > maxSum) {
        maxSum = tempSum;
      }
    }

    return maxSum;
  }

  // Time Limit Exceeded
  public int kConcatenationMaxSum_TimeLimitExceeded(int[] arr, int k) {

    int maxSum = 0;
    int tempSum = 0;
    for (int i = 0; i < arr.length; ) {
      tempSum += arr[i];
      if (tempSum < 0) {
        tempSum = 0;
      }

      if (tempSum > maxSum) {
        maxSum = tempSum;
      }
      if (i == arr.length - 1) {
        i = 0;
        k--;
      } else {
        i++;
      }
      if (k == 0) {
        return maxSum;
      }
    }

    return maxSum;
  }

  // 存在错误
  public int kConcatenationMaxSum(int[] arr, int k) {

    int maxSum = 0;
    int tempSum = 0;
    int allSum = 0;

    for (int i = 0; i < arr.length; i++) {
      allSum += arr[i];

      tempSum += arr[i];
      if (tempSum < 0) {
        tempSum = 0;
      }

      if (tempSum > maxSum) {
        maxSum = tempSum;
      }
    }
    if (allSum < 0) {
      allSum = 0;
    }

    if (k == 1) {
      return maxSum;
    } else {
      for (int i = 0; i < arr.length; i++) {

        tempSum += arr[i];
        if (tempSum < 0) {
          tempSum = 0;
        }

        if (tempSum > maxSum) {
          maxSum = tempSum;
        }
      }

      return maxSum + (allSum * (k - 2));
    }
  }


  /**
   * 它人解法 prefix + suffix + sum * (k - 2)
   *
   * @param arr
   * @param k
   * @return
   */
  int mod = (int) Math.pow(10, 9) + 7;

  public int kConcatenationMaxSum_1(int[] arr, int k) {
    long kadanes = kadanesAlgo(arr);
    if (k == 1) {
      return (int) kadanes;
    }
    long prefixSum = prefixSum(arr);
    long suffixSum = suffixSum(arr);
    long sum = 0;
    for (int i1 : arr) {
      sum += i1;
    }
    if (sum > 0) {
      return (int) (Math.max(((sum * (k - 2)) % mod + suffixSum % mod + prefixSum % mod) % mod,
        kadanes % mod));
    } else {
      return (int) (Math.max((prefixSum % mod + suffixSum % mod) % mod, kadanes % mod));
    }
  }

  public long kadanesAlgo(int[] ar) {

    long currentSum = 0;
    long maxSum = Integer.MIN_VALUE;
    for (int i = 0; i < ar.length; i++) {
      currentSum = currentSum > 0 ? (currentSum + ar[i]) % mod : ar[i];
      maxSum = Math.max(currentSum, maxSum);
    }
    return maxSum < 0 ? 0 : maxSum % mod;

  }

  public long prefixSum(int[] arr) {
    long currentSum = 0;
    long maxSum = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) {
      currentSum = (currentSum + arr[i]) % mod;
      maxSum = Math.max(maxSum, currentSum);
    }
    return maxSum;
  }

  public long suffixSum(int[] arr) {
    long currentSum = 0;
    long maxSum = Integer.MIN_VALUE;
    for (int i = arr.length - 1; i >= 0; i--) {
      currentSum = (currentSum + arr[i]) % mod;
      maxSum = Math.max(currentSum, maxSum);
    }
    return maxSum;
  }

  @Test
  public void test() {
    K_ConcatenationMaximumSum maximumSum = new K_ConcatenationMaximumSum();
    int[] arr = {1, 2};
    int result = maximumSum.kConcatenationMaxSum(arr, 3);
    Assert.assertEquals(9, result);
  }

  @Test
  public void test1() {
    K_ConcatenationMaximumSum maximumSum = new K_ConcatenationMaximumSum();
    int[] arr = {1, -2, 1};
    int result = maximumSum.kConcatenationMaxSum(arr, 5);
    Assert.assertEquals(2, result);
  }

  @Test
  public void test2() {
    K_ConcatenationMaximumSum maximumSum = new K_ConcatenationMaximumSum();
    int[] arr = {-1, -2};
    int result = maximumSum.kConcatenationMaxSum(arr, 7);
    Assert.assertEquals(0, result);
  }

  @Test
  public void test3() {
    K_ConcatenationMaximumSum maximumSum = new K_ConcatenationMaximumSum();
    int[] arr = {-5, -2, 0, 0, 3, 9, -2, -5, 4};
    int result = maximumSum.kConcatenationMaxSum_1(arr, 5);
    Assert.assertEquals(20, result);
  }


}
