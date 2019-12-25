package S001_100;

import org.junit.Test;

/**
 * Create by haifei on 24/12/2019 12:46 PM.
 */
public class S048_RotateImage {
  public void rotate(int[][] matrix) {

    int row = matrix.length;
    int col = matrix[0].length;

    int[][] res = new int[row][col];

    for (int i = row - 1; i >= 0; i--) {
      for (int j = 0; j < col; j++) {
        res[j][row - i - 1] = matrix[i][j];
      }
    }

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        matrix[i][j] = res[i][j];
      }
    }
  }

  @Test
  public void test() {
    int[][] arr = {
      {5, 1, 9, 11},
      {2, 4, 8, 10},
      {13, 3, 6, 7},
      {15, 14, 12, 16}
    };

    rotate(arr);


  }
}
