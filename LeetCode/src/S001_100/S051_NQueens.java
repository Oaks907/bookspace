package S001_100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Create by haifei on 15/1/2019 11:40 PM.
 * <p>
 * n 皇后问题
 * <p>
 * https://leetcode.com/problems/n-queens/
 */
public class S051_NQueens {

  public List<List<String>> solveNQueens(int n) {

    List<List<String>> lists = new ArrayList<>();

    char[][] arr = new char[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        arr[i][j] = 'Q';
      }
    }

    recursion(0, arr, lists, n);

    return lists;
  }

  private void recursion(int level, char[][] arr, List<List<String>> list, int maxLevel) {
    if (level == maxLevel) {
//      PrintUtils.printCharArray(arr);
//      System.out.println("---");
      list.add(addSolution(arr));
      return;
    }


    for (int j = 0; j < arr[0].length; j++) {
      char[][] copyArr = copyArr(arr);
      if (copyArr[level][j] == 'Q') {
        signArray(copyArr, level, j);
        recursion(level + 1, copyArr, list, maxLevel);
      }
    }
  }

  private void signArray(char[][] arr, int level, int col) {

    for (int i = level; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        if (arr[i][j] == '.') {
          continue;
        }

        if (i == level && j == col) {
          continue;
        }

        if (i == level || j == col || Math.abs(i - level) == Math.abs(j - col)) {
          arr[i][j] = '.';
        }
      }
    }
  }

  private char[][] copyArr(char[][] arr) {
    char[][] copy = new char[arr.length][arr[0].length];

    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        copy[i][j] = arr[i][j];
      }
    }
    return copy;
  }


  private List<String> addSolution(char[][] arr) {

    List<String> solution = new ArrayList<>();
    for (char[] anArr : arr) {
      solution.add(String.valueOf(anArr));
    }

    return solution;
  }

  public static void main(String[] args) {
    final S051_NQueens nQueens = new S051_NQueens();
    System.out.println(nQueens.solveNQueens(4));

    System.out.println(nQueens.solveNQueens_better(4));
  }


  public List<List<String>> solveNQueens_better(int n) {
    char[][] board = new char[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        board[i][j] = '.';
    List<List<String>> res = new ArrayList<List<String>>();
    dfs(board, 0, res);
    return res;
  }

  private void dfs(char[][] board, int colIndex, List<List<String>> res) {
    if (colIndex == board.length) {
      res.add(construct(board));
      return;
    }

    for (int i = 0; i < board.length; i++) {
      if (validate(board, i, colIndex)) {
        board[i][colIndex] = 'Q';
        dfs(board, colIndex + 1, res);
        board[i][colIndex] = '.';
      }
    }
  }

  private boolean validate(char[][] board, int x, int y) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < y; j++) {
        if (board[i][j] == 'Q' && (x + j == y + i || x + y == i + j || x == i || y == j))
          return false;
      }
    }

    return true;
  }

  private List<String> construct(char[][] board) {
    List<String> res = new LinkedList<>();
    for (int i = 0; i < board.length; i++) {
      String s = new String(board[i]);
      res.add(s);
    }
    return res;
  }
}
