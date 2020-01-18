package S801_S900;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 18/1/2020 8:54 PM.
 */
public class S852_PeakIndexinaMountainArray {

  public int peakIndexInMountainArray(int[] A) {

    int left = Integer.MIN_VALUE;

    for (int i = 0; i < A.length - 1; i++) {
      if (A[i] > left && A[i] > A[i + 1]) {
        return i;
      }
      left = A[i];
    }

    return A.length - 1;

  }

  public int peakIndexInMountainArray2(int[] A) {

    for (int i = 1; i + 1 < A.length; i++) {
      if (A[i] > A[i + 1]) {
        return i;
      }
    }

    return 0;
  }


  @Test
  public void test() {

    int[] arr = {0, 1, 0};

    int result = peakIndexInMountainArray(arr);

    Assert.assertEquals(1, result);
  }

  @Test
  public void test1() {

    int[] arr = {0, 2, 1, 0};

    int result = peakIndexInMountainArray(arr);

    Assert.assertEquals(1, result);
  }
}
