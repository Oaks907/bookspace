package S701_800;

/**
 * Create by haifei on 23/1/2018.
 */
public class S766_ToeplitzMatrix {
  public static boolean isToeplitzMatrix(int[][] matrix) {
    int row = matrix.length; //行
    int col = matrix[0].length; //列

    for (int i = row - 1; i > 0; i--) {
      for (int j = 1; j < col; j++) {
        if (matrix[i][j] == matrix[i - 1][j - 1]) {
          continue;
        }
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    int[][] matrix = {{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}};
    int[][] matrix1 = {{1, 2}, {2, 2}};
    System.out.println(isToeplitzMatrix(matrix));  //true
    System.out.println(isToeplitzMatrix(matrix1));  //false
  }
}
