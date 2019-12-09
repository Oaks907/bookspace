package S1201_S1300;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 9/12/2019 11:01 PM.
 */
public class S1277_CountSquareSubmatriceswithAllOnes {

  public int countSquares(int[][] matrix) {

    int[][] dp = new int[matrix.length][matrix[0].length];
    int count = 0;

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] == 0) {
          continue;
        }
        if (i - 1 < 0 || j - 1 < 0) {
          dp[i][j] = 1;
        } else {
          dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
        }

        count += dp[i][j];
      }
    }

    return count;
  }

  @Test
  public void test() {
    int[][] matrix = {
      {1, 0, 1},
      {1, 1, 0},
      {1, 1, 0}
    };

    int result = countSquares(matrix);

    Assert.assertEquals(7, result);
  }

}
