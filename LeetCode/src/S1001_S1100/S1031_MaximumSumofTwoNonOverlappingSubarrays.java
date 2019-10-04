package S1001_S1100;

import org.junit.Assert;
import org.junit.Test;
import utils.PrintUtils;

/**
 * https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/
 * Create by haifei on 5/10/2019 6:04 AM.
 */
public class S1031_MaximumSumofTwoNonOverlappingSubarrays {

  public int maxSumTwoNoOverlap(int[] A, int L, int M) {

    for (int i = 1; i < A.length; i++) {
      A[i] += A[i - 1];
    }

    PrintUtils.printArray(A);

    int res = A[L + M - 1], LMAX = A[L - 1], MMAX = A[M - 1];

    for (int i = L + M; i < A.length; i++) {
      LMAX = Math.max(LMAX, A[i - M] - A[i - L - M]);
      MMAX = Math.max(MMAX, A[i - L] - A[i - L - M]);

      res = Math.max(res, Math.max(LMAX + A[i] - A[i - M], MMAX + A[i] - A[i - L]));
    }

    return res;
  }

  @Test
  public void test() {
    int[] A = {0, 6, 5, 2, 2, 5, 1, 9, 4};

    int result = maxSumTwoNoOverlap(A, 1, 2);

    Assert.assertEquals(20, result);
  }

  @Test
  public void test1() {
    int[] A = {0, 6, 5, 2, 2, 5, 1, 9, 4};

    int result = maxSumTwoNoOverlap(A, 1, 2);

    Assert.assertEquals(20, result);
  }

  @Test
  public void test2() {
    int[] A = {3, 8, 1, 3, 2, 1, 8, 9, 0};

    int result = maxSumTwoNoOverlap(A, 3, 2);

    Assert.assertEquals(29, result);
  }

  @Test
  public void test3() {
    int[] A = {2, 1, 5, 6, 0, 9, 5, 0, 3, 8};

    int result = maxSumTwoNoOverlap(A, 4, 3);

    Assert.assertEquals(31, result);
  }
}
