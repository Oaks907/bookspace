package S601_700;

/**
 * Create by haifei on 11/1/2018.
 */
public class S695_MaxAreaOfIsland {
  public static int maxAreaOfIsland(int[][] grid) {
    int row = grid.length - 1;
    int col = grid[0].length - 1;
    int result = recuriveMethod(grid, 0, 0, row, col);


    for (int i = 0; i <= row; i++) {
      for (int j = 0; j <= col; j++) {
        grid[i][j] = recuriveMethod(grid, i, j, row, col);
        result = Math.max(result, grid[i][j]);
      }
    }

    return result;
  }

  public static int recuriveMethod(int[][] grid, int i, int j, int row, int col) {
    if (i < 0 || j < 0 || i > row || j > col) {
      return 0;
    }
    if (grid[i][j] == 1) {
      grid[i][j] = 0;
      return 1 +
        recuriveMethod(grid, i + 1, j, row, col) +
        recuriveMethod(grid, i - 1, j, row, col) +
        recuriveMethod(grid, i, j + 1, row, col) +
        recuriveMethod(grid, i, j - 1, row, col);
    }
    return 0;
  }

  public static void main(String[] args) {
    int[][] nums = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}};
    System.out.println(maxAreaOfIsland(nums));
  }
}
