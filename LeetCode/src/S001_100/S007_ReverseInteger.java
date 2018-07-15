package S001_100;

import java.util.Map;

/**
 * Create by haifei on 25/1/2018.
 */
public class S007_ReverseInteger {
  public static int reverse(int x) {
    boolean isFuShu = false;
    if (x < 0) {
      isFuShu = true;
      x = -x;
    }
    long result = 0;
    while (x > 0) {
      int val = x % 10;
      result = result * 10 + val;

      x = (x - val) / 10;
    }
    if (result > Integer.MAX_VALUE) {
      return 0;
    }

    if (isFuShu) {
      result = -result;
    }
    return (int) result;
  }

  public static void main(String[] args) {
    reverse(12345);
  }
}
