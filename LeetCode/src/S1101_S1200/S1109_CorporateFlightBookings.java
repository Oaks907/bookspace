package S1101_S1200;

import org.junit.Test;
import utils.PrintUtils;

/**
 * Create by haifei on 23/12/2019 8:07 PM.
 */
public class S1109_CorporateFlightBookings {

  public int[] corpFlightBookings(int[][] bookings, int n) {

    int[] res = new int[n];

    for (int[] booking : bookings) {
      res[booking[0] - 1] += booking[2];

      if (n > booking[1]) {
        res[booking[1]] -= booking[2];
      }
    }

    for (int i = 1; i < res.length; i++) {
      res[i] += res[i - 1];
    }

    return res;
  }

  @Test
  public void test() {
    int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};

    // 10,55,45,25,25
    PrintUtils.printArray(corpFlightBookings(bookings, 5));
  }
}
