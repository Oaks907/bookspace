package contest.contest_155;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 22/9/2019 10:38 AM.
 */
public class UglyNumberIII {

  /**
   * Time Limit Exceeded
   */
  public int nthUglyNumber(int n, int a, int b, int c) {

    int result = 0;
    int preValue = Integer.MIN_VALUE;
    int count = 0;
    int aindex = 1;
    int bindex = 1;
    int cindex = 1;

    while (count < n) {

      int aValue = a * aindex;
      int bValue = b * bindex;
      int cValue = c * cindex;

      int min = Math.min(Math.min(aValue, bValue), cValue);
      if (preValue >= min) {
        if (preValue >= aValue) {
          aindex++;
        } else if (preValue >= bValue) {
          bindex++;
        } else {
          cindex++;
        }
        continue;
      }

      result = min;
      if (result == aValue) {
        aindex++;
      } else if (result == bValue) {
        bindex++;
      } else {
        cindex++;
      }
      preValue = result;
      count++;
    }
    return result;
  }

  // Binary Search
  public int nthUglyNumber_1(int n, int a, int b, int c) {
    int start = 1;
    int end = Integer.MAX_VALUE;

    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (countUgly(a, b, c, mid) >= n) {
        end = mid;
      } else {
        start = mid;
      }
    }

    return end;
  }

  private int countUgly(int a, int b, int c, int target) {
    int count = 0;
    count += (target / a + target / b + target / c);
    count -= (target / gcm(a, b) + target / gcm(a, c) + target / gcm(b, c));
    count += (target / gcm(a, gcm(b, c)));
    return count;
  }

  private long gcm(long a, long b) {
    return a * b / gcd(a, b);
  }

  private long gcd(long a, long b) {
    if (b != 0) {
      return gcd(b, a % b);
    }
    return a;
  }


  @Test
  public void test4() {
    int result = nthUglyNumber(1000000000, 2, 217983653, 336916467);
    Assert.assertEquals(1999999984, result);

    result = nthUglyNumber_1(1000000000, 2, 217983653, 336916467);

    Assert.assertEquals(1999999984, result);
  }

  @Test
  public void test1() {
    int result = nthUglyNumber(3, 2, 3, 5);
    Assert.assertEquals(4, result);

    result = nthUglyNumber_1(3, 2, 3, 5);
    Assert.assertEquals(4, result);
  }

  @Test
  public void test2() {
    int result = nthUglyNumber(4, 2, 3, 4);

    Assert.assertEquals(6, result);
  }

  @Test
  public void test3() {
    int result = nthUglyNumber(5, 2, 11, 13);

    Assert.assertEquals(10, result);
  }
}
