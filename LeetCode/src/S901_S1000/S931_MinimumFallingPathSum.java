package S901_S1000;

import com.sun.org.apache.regexp.internal.REUtil;

import java.security.interfaces.RSAKey;

/**
 * Create by haifei on 1/12/2018 4:05 PM.
 * <p>
 * https://leetcode.com/problems/minimum-falling-path-sum/
 */
public class S931_MinimumFallingPathSum {

  public int minFallingPathSum(int[][] A) {
    int row = A.length;
    int col = A[0].length;
    int[][] temp = new int[row][col];

    System.arraycopy(A[0], 0, temp[0], 0, col);


    for (int i = 1; i < row; i++) {
      for (int j = 0; j < col; j++) {

        int pre = Integer.MAX_VALUE;
        int next = Integer.MAX_VALUE;

        if (j > 0) {
          pre = temp[i - 1][j - 1];
        }

        int cur = temp[i - 1][j];

        if (j < col - 1) {
          next = temp[i - 1][j + 1];
        }

        int lowest = Math.min(pre, Math.min(cur, next));
        temp[i][j] = A[i][j] + lowest;
      }
    }

    int result = Integer.MAX_VALUE;
    for (int i = 0; i < col; i++) {
      if (temp[row - 1][i] < result) {
        result = temp[row - 1][i];
      }
    }

    return result;
  }

  public static void main(String[] args) {
    int[][] A = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    final S931_MinimumFallingPathSum pathSum = new S931_MinimumFallingPathSum();
    System.out.println(pathSum.minFallingPathSum(A));
  }
}
