package S601_700;

/**
 * Create by haifei on 17/1/2018.
 */
public class S661_ImageSmoother {
  public static int[][] imageSmoother(int[][] M) {
    int row = M.length;  //行
    int col = M[0].length; //列

    int result[][] = new int[row][col];

    for (int iRow = 0; iRow < row; iRow++) {

      for (int iCol = 0; iCol < col; iCol++) {
        int allCount = 1;
        int haveCellCount = 0;

        //当前点
        haveCellCount += M[iRow][iCol];

        //正上
        if (iRow - 1 >= 0) {
          allCount++;
          haveCellCount += M[iRow - 1][iCol];
        }

        //右上
        if (iRow - 1 >= 0 && iCol + 1 < col) {
          allCount++;
          haveCellCount += M[iRow - 1][iCol + 1];
        }

        //右
        if (iCol + 1 < col) {
          allCount++;
          haveCellCount += M[iRow][iCol + 1];
        }

        //右下
        if (iRow + 1 < row && iCol + 1 < col) {
          allCount++;
          haveCellCount += M[iRow + 1][iCol + 1];
        }

        //下
        if (iRow + 1 < row) {
          allCount++;
          haveCellCount += M[iRow + 1][iCol];
        }

        //左下
        if (iRow + 1 < row && iCol - 1 >= 0) {
          allCount++;
          haveCellCount += M[iRow + 1][iCol - 1];
        }

        //左
        if (iCol - 1 >= 0) {
          allCount++;
          haveCellCount += M[iRow][iCol - 1];
        }

        //左上
        if (iRow - 1 >= 0 && iCol - 1 >= 0) {
          allCount++;
          haveCellCount += M[iRow - 1][iCol - 1];
        }

        if (allCount == 0) {
          result[iRow][iCol] = 0;
        } else {
          result[iRow][iCol] = haveCellCount / allCount;
        }
      }
    }

    return result;
  }

  public static void printArray(int[][] nums) {

    int row = nums.length;  //行
    int col = nums[0].length; //列

    for (int iRow = 0; iRow < row; iRow++) {
      for (int iCol = 0; iCol < col; iCol++) {
        System.out.print(nums[iRow][iCol] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int[][] m = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
    int[][] m1 = {{2, 3, 4}, {5, 6, 7}, {8, 9, 10}, {11, 12, 13}, {14, 15, 16}};
    printArray(imageSmoother(m));
    printArray(imageSmoother(m1));
  }
}
