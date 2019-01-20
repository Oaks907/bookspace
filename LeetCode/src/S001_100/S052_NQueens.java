package S001_100;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 16/1/2019 1:03 PM.
 * N皇后问题，同051
 * https://leetcode.com/problems/n-queens-ii/
 */
public class S052_NQueens {


  public int totalNQueens(int n) {

    char[][] charArray = new char[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        charArray[i][j] = '.';
      }
    }

    List<List<String>> lists = new ArrayList<>();
    dfs(charArray, lists, 0);

    return lists.size();
  }

  private void dfs(char[][] array, List<List<String>> lists, int column) {
    if (column == array[0].length) {
//      PrintUtils.printCharArray(array);
//      System.out.println("====");

      lists.add(construct(array));
      return;
    }

    for (int i = 0; i < array.length; i++) {
      if (isValid(array, i, column)) {
        array[i][column] = 'Q'; //valid验证通过后，表明该位置能够放置Q
        dfs(array, lists, column + 1);
        array[i][column] = '.';
      }
    }
  }

  /**
   * 判断坐标（x,y）是否能够放置在数组中
   *
   * @param array
   * @param x
   * @param y
   * @return
   */
  private boolean isValid(char[][] array, int x, int y) {
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[0].length; j++) {
        if (array[i][j] == 'Q' && ((Math.abs(x - i) == Math.abs(y - j)) || i == x || j == y)) {
          return false;
        }
      }
    }
    return true;
  }

  private List<String> construct(char[][] board) {
    List<String> res = new ArrayList<>();
    for (int i = 0; i < board.length; i++) {
      String s = new String(board[i]);
      res.add(s);
    }
    return res;
  }


  /**
   * 使用位运算优化，最高效的解法
   * <p>
   * https://www.jianshu.com/p/d16707207de8
   *
   * @param n
   * @return
   */
  public int totalNQueens_bit(int n) {

    N = n;
    dfs(0, 0, 0, 0);

    return count;
  }

  int count;
  int N;

  private void dfs(int row, int col, int pie, int nia) {

    if (row >= N) {
      count++;
      return;
    }

    //(col | pie | nia) 当一个元素准备放在 col 的时候，根据当前放了 col 后，根据上面的各个元素算出下一行能放的位置
    //
    int bits = (~(col | pie | nia) & ((1 << N) - 1));

    //此时 bits 二进制位中 1 的位置代表能够放置新的元素
    while (bits > 0) {
      //获取右边第一个 1
      int P = bits & -bits;

      dfs(row + 1, col | P, (pie | P) << 1, (nia | P) >> 1);

      //将最后的 1 置为 0
      bits &= (bits - 1);
    }
  }

  public static void main(String[] args) {

    final S052_NQueens s052_nQueens = new S052_NQueens();
    System.out.println(s052_nQueens.totalNQueens(4));

    System.out.println(s052_nQueens.totalNQueens_bit(4));
  }
}
