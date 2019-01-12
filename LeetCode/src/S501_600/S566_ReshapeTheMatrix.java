package S501_600;

import utils.PrintUtils;

/**
 * Create by haifei on 24/1/2018.
 */
public class S566_ReshapeTheMatrix {
  public static int[][] matrixReshape(int[][] nums, int r, int c) {
    int row = nums.length;
    int col = nums[0].length;
    if (row * col < r * c) {
      return nums;
    }
    int[][] result = new int[r][c];
    int rowResult = 0;
    int colResult = 0;

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        int val = nums[i][j];
        result[rowResult][colResult++] = val;
        if (colResult >= c ) {
          colResult = 0;
          rowResult++;
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int[][] nums = {{1, 2}, {3, 4}};
    PrintUtils.printArray(matrixReshape(nums, 1, 4)); //1, 2, 3, 4,
  }
}
