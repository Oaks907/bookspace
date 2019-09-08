package S801_S900;

import org.junit.Test;
import utils.PrintUtils;

/**
 * Create by haifei on 3/9/2019 11:46 PM.
 */
public class S832_FlippingAnImage {

  public int[][] flipAndInvertImage(int[][] A) {

    for (int i = 0; i < A.length; i++) {
      int left = 0;
      int right = A[0].length - 1;
      while (left <= right) {
        int temp = A[i][left];
        A[i][left] = A[i][right] == 0 ? 1 : 0;
        A[i][right] = temp == 0 ? 1 : 0;
        left++;
        right--;
      }
    }

    return A;
  }

  @Test
  public void test1() {
    int[][] A = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};
    S832_FlippingAnImage flippingAnImage = new S832_FlippingAnImage();
    PrintUtils.printArray(flippingAnImage.flipAndInvertImage(A));
  }

  @Test
  public void test2() {
    int[][] A = {{1, 1, 0, 0}, {1, 0, 0, 1}, {0, 1, 1, 1}, {1, 0, 1, 0}};
    S832_FlippingAnImage flippingAnImage = new S832_FlippingAnImage();
    PrintUtils.printArray(flippingAnImage.flipAndInvertImage(A));
  }
}
