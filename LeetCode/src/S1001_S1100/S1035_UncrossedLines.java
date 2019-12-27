package S1001_S1100;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 26/12/2019 10:32 PM.
 */
public class S1035_UncrossedLines {

  public int maxUncrossedLines(int[] A, int[] B) {

    int alen = A.length;
    int blen = B.length;
    int[][] dp = new int[alen + 1][blen + 1];

    for (int i = 1; i <= alen; i++) {
      for (int j = 1; j <= blen; j++) {
        if (A[i - 1] == B[j - 1]) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    return dp[alen][blen];
  }

  @Test
  public void test() {
    int[] arr1 = {1, 4, 2};
    int[] arr2 = {1, 2, 4};

    int result = maxUncrossedLines(arr1, arr2);

    Assert.assertEquals(2, result);
  }

  @Test
  public void test1() {
    int[] arr1 = {2, 5, 1, 2, 5};
    int[] arr2 = {10, 5, 2, 1, 5, 2};

    int result = maxUncrossedLines(arr1, arr2);

    Assert.assertEquals(3, result);
  }

  @Test
  public void test2() {
    int[] arr1 = {1, 3, 7, 1, 7, 5};
    int[] arr2 = {1, 9, 2, 5, 1};

    int result = maxUncrossedLines(arr1, arr2);

    Assert.assertEquals(2, result);
  }
}
