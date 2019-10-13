package S1201_S1300;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/queens-that-can-attack-the-king/
 * Create by haifei on 13/10/2019 10:56 PM.
 */
public class S1222_QueensThatCanAttacktheKing {

  // 8x8 棋盘上，分别遍历 k 的八个方向，找到第一值就行
  public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
    int N = 8;
    int[][] chessBoard = new int[N][N];

    for (int i = 0; i < queens.length; i++) {
      chessBoard[queens[i][0]][queens[i][1]] = 1;
    }

    int row = king[0];
    int col = king[1];

    List<List<Integer>> result = new ArrayList<>();
    // 上方, 列固定
    while (row >= 0) {
      if (chessBoard[row][col] == 1) {
        result.add(Arrays.asList(row, col));
        break;
      }
      row--;
    }

    row = king[0];
    col = king[1];

    // 右上
    while (row >= 0 && col < N) {
      if (chessBoard[row][col] == 1) {
        result.add(Arrays.asList(row, col));
        break;
      }
      row--;
      col++;
    }

    row = king[0];
    col = king[1];

    // 右方
    while (col < N) {
      if (chessBoard[row][col] == 1) {
        result.add(Arrays.asList(row, col));
        break;
      }
      col++;
    }

    row = king[0];
    col = king[1];

    // 右下
    while (row < N && col < N) {
      if (chessBoard[row][col] == 1) {
        result.add(Arrays.asList(row, col));
        break;
      }
      row++;
      col++;
    }

    row = king[0];
    col = king[1];

    // 下方
    while (row < N) {
      if (chessBoard[row][col] == 1) {
        result.add(Arrays.asList(row, col));
        break;
      }
      row++;
    }

    row = king[0];
    col = king[1];

    // 左下
    while (row < N && col >= 0) {
      if (chessBoard[row][col] == 1) {
        result.add(Arrays.asList(row, col));
        break;
      }
      row++;
      col--;
    }

    row = king[0];
    col = king[1];

    // 左边
    while (col >= 0) {
      if (chessBoard[row][col] == 1) {
        result.add(Arrays.asList(row, col));
        break;
      }
      col--;
    }

    row = king[0];
    col = king[1];

    // 左上
    while (row >= 0 && col >= 0) {
      if (chessBoard[row][col] == 1) {
        result.add(Arrays.asList(row, col));
        break;
      }
      row--;
      col--;
    }

    return result;
  }

  @Test
  public void test() {
    int[][] queens = {{0, 1}, {1, 0}, {4, 0}, {0, 4}, {3, 3}, {2, 4}};
    int[] king = {0, 0};

    List<List<Integer>> result = queensAttacktheKing(queens, king);
    System.out.println(result);

    Assert.assertEquals(3, result.size());
  }

  @Test
  public void test1() {
    int[][] queens = {{5, 6}, {7, 7}, {2, 1}, {0, 7}, {1, 6}, {5, 1}, {3, 7}, {0, 3}, {4, 0}, {1,
      2}, {6, 3}, {5, 0}, {0, 4}, {2, 2}, {1, 1}, {6, 4}, {5, 4}, {0, 0}, {2, 6}, {4, 5}, {5, 2},
      {1, 4}, {7, 5}, {2, 3}, {0, 5}, {4, 2}, {1, 0}, {2, 7}, {0, 1}, {4, 6}, {6, 1}, {0, 6}, {4,
      3}, {1, 7}};
    int[] king = {3, 4};

    List<List<Integer>> result = queensAttacktheKing(queens, king);
    System.out.println(result);

    Assert.assertEquals(7, result.size());
  }

}
