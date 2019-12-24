package S001_100;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 23/12/2019 8:52 PM.
 */
public class S062_UniquePaths {

  public int uniquePaths(int m, int n) {

    int[][] matrix = new int[m][n];
    for (int i = 0; i < m; i++) {
      matrix[i][0] = 1;
    }
    for (int i = 0; i < n; i++) {
      matrix[0][i] = 1;
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
      }
    }

    return matrix[m - 1][n - 1];
  }

  @Test
  public void test1() {
    int result = uniquePaths(3, 3);

    Assert.assertEquals(6, result);
  }

  @Test
  public void test2() {
    int result = uniquePaths(7, 3);

    Assert.assertEquals(28, result);
  }

  @Test
  public void test3() {
    int result = uniquePaths(3, 2);

    Assert.assertEquals(3, result);
  }
}
