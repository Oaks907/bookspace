package S801_S900;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 7/10/2019 4:32 PM.
 */
public class S896_MonotonicArray {

  public boolean isMonotonic(int[] A) {

    if (A == null || 0 == A.length || 1 == A.length) {
      return true;
    }

    boolean isIncreasing = A[A.length - 1] >= A[0];

    for (int i = 1; i < A.length; i++) {
      if (isIncreasing) {
        if (A[i - 1] > A[i]) {
          return false;
        }
      } else {
        if (A[i - 1] < A[i]) {
          return false;
        }
      }
    }

    return true;
  }

  @Test
  public void test() {
    int[] A = {1, 2, 2, 3};

    boolean result = isMonotonic(A);

    Assert.assertTrue(result);
  }

  @Test
  public void test1() {
    int[] A = {6, 5, 4, 4};

    boolean result = isMonotonic(A);

    Assert.assertTrue(result);
  }

  @Test
  public void test2() {
    int[] A = {1, 3, 2};

    boolean result = isMonotonic(A);

    Assert.assertFalse(result);
  }

  @Test
  public void tes3() {
    int[] A = {1, 2, 4, 5};

    boolean result = isMonotonic(A);

    Assert.assertTrue(result);
  }

  @Test
  public void test4() {
    int[] A = {1, 1, 1};

    boolean result = isMonotonic(A);

    Assert.assertTrue(result);
  }


}
