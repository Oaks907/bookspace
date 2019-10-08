package S1101_S1200;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 7/10/2019 5:04 PM.
 */
public class S1137_NthTribonacciNumber {

  // TLE
  public int tribonacci(int n) {
    if (n == 0) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }
    if (n == 2) {
      return 1;
    }

    return tribonacci(n - 3) + tribonacci(n - 2) + tribonacci(n - 1);
  }

  //
  public int tribonacci1(int n) {
    if (n == 0) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }
    if (n == 2) {
      return 1;
    }

    int[] arr = new int[n + 1];
    arr[0] = 0;
    arr[1] = 1;
    arr[2] = 1;

    for (int i = 3; i < arr.length; i++) {
      arr[i] = arr[i - 3] + arr[i - 2] + arr[i - 1];
    }

    return arr[n];
  }

  @Test
  public void test() {
    int result = tribonacci(4);

    Assert.assertEquals(4, result);

    result = tribonacci1(4);

    Assert.assertEquals(4, result);
  }


  @Test
  public void test1() {
    int result = tribonacci(25);

    Assert.assertEquals(1389537, result);

    result = tribonacci1(25);

    Assert.assertEquals(1389537, result);
  }
}
