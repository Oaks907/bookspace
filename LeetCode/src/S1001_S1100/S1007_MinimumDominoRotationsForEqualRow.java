package S1001_S1100;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 8/1/2020 9:54 PM.
 */
public class S1007_MinimumDominoRotationsForEqualRow {

  // 直接存在返回0，如何交换都不行返回 -1
  public int minDominoRotations(int[] A, int[] B) {

    int[] countA = new int[7];
    int[] countB = new int[7];
    int[] countSame = new int[7];
    int len = A.length;

    for (int i = 0; i < len; i++) {
      countA[A[i]]++;
      countB[B[i]]++;
      if (A[i] == B[i]) {
        countSame[A[i]]++;
      }
    }

    for (int i = 1; i < 7; i++) {
      if (countA[i] + countB[i] - countSame[i] == len) {
        return len - Math.max(countA[i], countB[i]);
      }
    }

    return -1;
  }

  @Test
  public void test() {

    int[] A = {2, 1, 2, 4, 2, 2}, B = {5, 2, 6, 2, 3, 2};

    int result = minDominoRotations(A, B);

    Assert.assertEquals(2, result);
  }

  @Test
  public void test1() {

  }
}
