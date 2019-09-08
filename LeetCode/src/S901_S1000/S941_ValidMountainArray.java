package S901_S1000;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Create by haifei on 27/3/2019 8:30 AM.
 * https://leetcode.com/problems/valid-mountain-array/
 */
public class S941_ValidMountainArray {

  public boolean validMountainArray(int[] A) {

    if (A == null || A.length == 0 || A.length < 3) {
      return false;
    }

    int leftIndex = 0;
    int rightIndex = A.length - 1;

    int preVal = A[0];

    for (int i = 1; i < A.length; i++) {
      if (A[i] > preVal) {
        preVal = A[i];
        leftIndex = i;
      } else {
        break;
      }
    }

    preVal = A[A.length - 1];

    for (int i = A.length - 2; i >= 0; i--) {
      if (A[i] > preVal) {
        preVal = A[i];
        rightIndex = i;
      } else {
        break;
      }
    }

    if (leftIndex == 0 || rightIndex == A.length - 1) {
      return false;
    }

    return leftIndex == rightIndex;
  }

  S941_ValidMountainArray validMountainArray = null;

  @Before
  public void before() {
    validMountainArray = new S941_ValidMountainArray();
  }


  @Test
  public void test3() {

    int[] arr = {0, 3, 2, 1};

    final boolean isMountain = validMountainArray.validMountainArray(arr);
    Assert.assertTrue(isMountain);
  }


  @Test
  public void test2() {

    int[] arr = {3, 5, 5};

    final boolean isMountain = validMountainArray.validMountainArray(arr);
    Assert.assertFalse(isMountain);
  }

  @Test
  public void test1() {

    int[] arr = {2, 1};

    final boolean isMountain = validMountainArray.validMountainArray(arr);
    Assert.assertFalse(isMountain);
  }
}
