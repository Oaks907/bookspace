package com.haifei;

/**
 * Create by haifei on 24/6/2018.
 */
public class S03_SearMatrix {

  public static boolean searchMatrix(int[][] matrix, int target) {
    if (null == matrix) {
      return false;
    }
    int row = matrix.length;
    if (row == 0) {
      return false;
    }
    int col = matrix[0].length;
    int curCol = col - 1;
    for (int i = 0; i < row; i++) {
      for (int j = curCol; j >= 0; j--) {
        if (matrix[i][j] == target) {
          return true;
        } else if (matrix[i][j] > target) {
          curCol--;
        } else if (matrix[i][j] < target) {
          break;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    int[][] array = {
      {1, 3, 5, 7},
      {10, 11, 16, 20},
      {23, 30, 34, 50}
    };

    System.out.println(searchMatrix(array, 6));
  }
}
