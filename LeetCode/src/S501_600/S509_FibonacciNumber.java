package S501_600;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/fibonacci-number/
 * Create by haifei on 7/10/2019 4:58 PM.
 */
public class S509_FibonacciNumber {

  public int fib(int N) {
    if (N == 0) {
      return 0;
    }
    if (N == 1) {
      return 1;
    }

    return fib(N - 1) + fib(N - 2);
  }

  @Test
  public void test() {
    int result = fib(2);

    Assert.assertEquals(1L, result);
  }

  @Test
  public void test1() {
    int result = fib(3);

    Assert.assertEquals(2L, result);
  }

  @Test
  public void test2() {
    int result = fib(4);

    Assert.assertEquals(3L, result);
  }

}
