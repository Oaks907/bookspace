package S001_100;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 24/12/2019 8:54 AM.
 */
public class S063_UniquePathsII {

  public int uniquePathsWithObstacles(int[][] obstacleGrid) {

    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;

    int[][] matrix = new int[m][n];
    matrix[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;

    for (int i = 1; i < m; i++) {
      if (obstacleGrid[i][0] == 1) {
        matrix[i][0] = 0;
      } else {
        matrix[i][0] = matrix[i - 1][0];
      }
    }
    for (int i = 1; i < n; i++) {
      if (obstacleGrid[0][i] == 1) {
        matrix[0][i] = 0;
      } else {
        matrix[0][i] = matrix[0][i - 1];
      }
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (obstacleGrid[i][j] == 1) {
          matrix[i][j] = 0;
        } else {
          matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
        }
      }
    }

    return matrix[m - 1][n - 1];
  }

  @Test
  public void test1() {

    int[][] arr = {
      {0, 0, 0},
      {0, 1, 0},
      {0, 0, 0}
    };

    int result = uniquePathsWithObstacles(arr);

    Assert.assertEquals(2, result);
  }
}
