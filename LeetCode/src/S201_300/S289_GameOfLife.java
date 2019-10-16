package S201_300;

import org.junit.Test;
import utils.PrintUtils;

/**
 * https://leetcode.com/problems/game-of-life/
 * Create by haifei on 16/10/2019 9:02 AM.
 */
public class S289_GameOfLife {

  public void gameOfLife(int[][] board) {
    int row = board.length;
    int col = board[0].length;

    int[][] neighbors = new int[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        neighbors[i][j] = getNeighborsNumber(board, i, j);
      }
    }

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {

        int neighbor = neighbors[i][j];

        if (board[i][j] == 0) { // live
          if (neighbor == 3) {
            board[i][j] = 1;
          }
        } else { // not live
          if (neighbor < 2 || neighbor > 3) {
            board[i][j] = 0;
          }
        }
      }
    }
  }

  public int getNeighborsNumber(int[][] arr, int i, int j) {
    int row = arr.length;
    int col = arr[0].length;

    int count = 0;
    // 左上
    if (i - 1 >= 0 && j - 1 >= 0) {
      count += arr[i - 1][j - 1] == 1 ? 1 : 0;
    }
    // 上
    if (i - 1 >= 0) {
      count += arr[i - 1][j] == 1 ? 1 : 0;
    }
    // 右上
    if (i - 1 >= 0 && j + 1 < col) {
      count += arr[i - 1][j + 1] == 1 ? 1 : 0;
    }
    // 右
    if (j + 1 < col) {
      count += arr[i][j + 1] == 1 ? 1 : 0;
    }
    // 右下
    if (i + 1 < row && j + 1 < col) {
      count += arr[i + 1][j + 1] == 1 ? 1 : 0;
    }
    // 下
    if (i + 1 < row) {
      count += arr[i + 1][j] == 1 ? 1 : 0;
    }
    // 左下
    if (i + 1 < row && j - 1 >= 0) {
      count += arr[i + 1][j - 1] == 1 ? 1 : 0;
    }
    // 左
    if (j - 1 >= 0) {
      count += arr[i][j - 1] == 1 ? 1 : 0;
    }

    return count;
  }

  @Test
  public void test() {
    int[][] board = {
      {0, 1, 0},
      {0, 0, 1},
      {1, 1, 1},
      {0, 0, 0}
    };

    gameOfLife(board);
  }
}
