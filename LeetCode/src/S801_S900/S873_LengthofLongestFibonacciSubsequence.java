package S801_S900;

import org.junit.Assert;
import org.junit.Test;
import utils.PrintUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Create by haifei on 30/12/2019 12:48 PM.
 */
public class S873_LengthofLongestFibonacciSubsequence {

  // Time Limit Exceeded
  public int lenLongestFibSubseq(int[] A) {
    int maxCount = 0;
    HashSet<Integer> set = new HashSet<>();
    for (int i : A) {
      set.add(i);
    }

    for (int i = 0; i < A.length - 1; i++) {
      for (int j = i + 1; j < A.length; j++) {
        int first = A[i];
        int second = A[j];
        int count = 2;
        while (set.contains(first + second)) {
          count++;
          int temp = first;
          first = second;
          second = temp + second;
        }

        if (count > 2 && maxCount < count) {
          maxCount = count;
        }
      }
    }
    return maxCount;
  }


  public int test2(int[] A) {
    int n = A.length;
    int max = 0;
    int[][] dp = new int[n][n];
    for (int i = 1; i < n; i++) {
      int l = 0, r = i - 1;
      while (l < r) {
        int sum = A[l] + A[r];
        if (sum > A[i]) {
          r--;
        } else if (sum < A[i]) {
          l++;
        } else {
          dp[r][i] = dp[l][r] + 1;
          max = Math.max(max, dp[r][i]);
          r--;
          l++;
        }
      }
    }

    PrintUtils.printArray(dp);
    return max == 0 ? 0 : max + 2;
  }

  @Test
  public void test() {
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};

    int result = lenLongestFibSubseq(arr);
    test2(arr);

    Assert.assertEquals(5, result);
  }

  @Test
  public void test1() {
    int[] arr = {1, 3, 7, 11, 12, 14, 18};

    int result = lenLongestFibSubseq(arr);

    Assert.assertEquals(3, result);
  }
}
