package S1001_S1100;

import org.junit.Assert;
import org.junit.Test;
import utils.PrintUtils;

/**
 * Create by haifei on 7/1/2020 9:36 PM.
 */
public class S1020_numberOfEnclaves {

  public int numEnclaves(int[][] A) {

    int row = A.length;
    int col = A[0].length;
    boolean[][] dp = new boolean[row][col];

    // top
    for (int i = 0; i < col; i++) {
      if (A[0][i] == 1 && !dp[0][i]) {
        spread(dp, A, 0, i);
      }
    }

//    PrintUtils.printArrayBoolean(dp);
//    System.out.println("-----");

    // right
    for (int i = 0; i < row; i++) {
      if (A[i][col - 1] == 1 && !dp[i][col - 1]) {
        spread(dp, A, i, col - 1);
      }
    }
//    PrintUtils.printArrayBoolean(dp);
//    System.out.println("-----");

    // bottom
    for (int i = 0; i < col; i++) {
      if (A[row - 1][i] == 1 && !dp[row - 1][i]) {
        spread(dp, A, row - 1, i);
      }
    }

//    PrintUtils.printArrayBoolean(dp);
//    System.out.println("-----");

    // left
    for (int i = 0; i < row; i++) {
      if (A[i][0] == 1 && !dp[i][0]) {
        spread(dp, A, i, 0);
      }
    }

//    PrintUtils.printArrayBoolean(dp);
//    System.out.println("-----");


    int count = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (A[i][j] == 1 && !dp[i][j]) {
          count++;
        }
      }
    }

    return count;
  }

  /**
   * 由坐标x，y向四周传播
   *
   * @param dp  传播情况
   * @param arr
   * @param x
   * @param y
   */
  public void spread(boolean[][] dp, int[][] arr, int x, int y) {
    int row = arr.length;
    int col = arr[0].length;

    if (x < 0 || x >= row || y < 0 || y >= col) {
      return;
    }

    // had processed
    if (dp[x][y]) {
      return;
    }

    if (arr[x][y] == 0) {
      return;
    }

    dp[x][y] = true;

    // top
    spread(dp, arr, x - 1, y);
    // right
    spread(dp, arr, x, y + 1);
    // bottom
    spread(dp, arr, x + 1, y);
    // left
    spread(dp, arr, x, y - 1);
  }

  @Test
  public void test() {

    int[][] arr = {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};

    int result = numEnclaves(arr);

    Assert.assertEquals(3, result);
  }

  @Test
  public void test1() {

    int[][] arr = {{0, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}};

    int result = numEnclaves(arr);

    Assert.assertEquals(0, result);
  }


}
