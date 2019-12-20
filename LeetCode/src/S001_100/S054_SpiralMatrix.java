package S001_100;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 17/12/2019 1:06 PM.
 */
public class S054_SpiralMatrix {

  public List<Integer> spiralOrder(int[][] matrix) {

    List<Integer> list = new ArrayList<>();
    if(matrix.length == 0 || matrix[0].length == 0) return list;

    int left = 0;
    int right = matrix[0].length - 1;
    int top = 0;
    int bottom = matrix.length - 1;


    while (true) {

      // left to right
      for (int i = left; i <= right; i++) {
        list.add(matrix[top][i]);
      }
      top++;
      if (left > right || top > bottom) break;

      // top to bottom
      for (int i = top; i <= bottom; i++) {
        list.add(matrix[i][right]);
      }
      right--;
      if (left > right || top > bottom) break;

      // right to left
      for (int i = right; i >= left; i--) {
        list.add(matrix[bottom][i]);
      }
      bottom--;
      if (left > right || top > bottom) break;

      // bottom to top
      for (int i = bottom; i >= top; i--) {
        list.add(matrix[i][left]);
      }
      left++;
      if (left > right || top > bottom) break;
    }

    return list;
  }

  @Test
  public void test() {

    int[][] matix = {
      { 1, 2, 3 },
      { 4, 5, 6 },
      { 7, 8, 9 }
    };

    System.out.println(spiralOrder(matix));
  }
}
