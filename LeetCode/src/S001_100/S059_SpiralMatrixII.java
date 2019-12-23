package S001_100;

import org.junit.Test;
import utils.PrintUtils;

/**
 * Create by haifei on 23/12/2019 8:21 PM.
 */
public class S059_SpiralMatrixII {

  public int[][] generateMatrix(int n) {

    int[][] res = new int[n][n];

    int left = 0;
    int right = n - 1;
    int top = 0;
    int bottom = n - 1;
    int start = 1;

    while (true) {

      // left to right
      for (int i = left; i <= right; i++) {
        res[top][i] = start++;
      }

      top++;

      if (left > right || top > bottom) {
        break;
      }

      // top to bottom
      for (int i = top; i <= bottom; i++) {
        res[i][right] = start++;
      }

      right--;

      if (left > right || top > bottom) {
        break;
      }

      // right to left
      for (int i = right; i >= left; i--) {
        res[bottom][i] = start++;
      }

      bottom--;

      if (left > right || top > bottom) {
        break;
      }

      // bottom to top
      for (int i = bottom; i >= top; i--) {
        res[i][left] = start++;
      }

      left++;

      if (left > right || top > bottom) {
        break;
      }
    }

    return res;
  }

  @Test
  public void test() {
    PrintUtils.printArray(generateMatrix(3));
  }

}
