package com.haifei;

/**
 * Create by haifei on 1/8/2018.
 */
public class S20_PrintMatrix {

  public void printMatrixClockwisely(int[][] numbers) {
    if (null == numbers) {
      return;
    }
    int row = numbers.length;
    int col = numbers[0].length;
    int start = 0;
    while (start * 2 < row && start * 2 < col) {
      printMatrixInCircle(numbers, start);
      start++;
    }

  }

  private void printMatrixInCircle(int[][] numbers, int start) {
    int row = numbers.length; //行
    int col = numbers[0].length;  //列

    int endX = col - 1 - start;
    int endY = row - 1 - start;

    //从左向右打印
    for (int i = start; i <= endX; i++) {
      print(numbers[start][i]);
    }

    //从上向下打印
    if (start < endY) {
      for (int i = start + 1; i <= endY; i++) {
        print(numbers[i][endX]);
      }
    }


    //从右向左打印
    if (start < endX && start < endY) {
      for (int i = endX - 1; i >= start; i--) {
        print(numbers[endY][i]);
      }
    }

    if (start < endX && start < endY - 1) {
      for (int i = endY - 1; i >= start + 1; i--) {
        print(numbers[i][start]);
      }
    }
  }

  private void print(int number) {
    System.out.print(number + " ");
  }

  public static void main(String[] args) {
    int[][] numbers = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
    new S20_PrintMatrix().printMatrixClockwisely(numbers);
  }
}
