package contest.contest_159;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 20/10/2019 10:32 AM.
 */
public class CheckIfItIsaStraightLine {

  public boolean checkStraightLine(int[][] coordinates) {
    if (null == coordinates || 0 == coordinates.length || coordinates.length < 2) {
      return false;
    }

    for (int i = 2; i < coordinates.length; i++) {
      // (y2 - y1) * (x1 - x0) = (x2 - x1) * (y1 - y0);

      int valueOne =
        (coordinates[i][1] - coordinates[i - 1][1]) * (coordinates[i - 1][0] - coordinates[i - 2][0]);
      int valueTwo =
        (coordinates[i][0] - coordinates[i-1][0]) * (coordinates[i-1][1] - coordinates[i-2][1]);
      if (valueOne != valueTwo) {
        return false;
      }
    }

    return true;
  }

  @Test
  public void test() {
    int[][] arr = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}};
    boolean result = checkStraightLine(arr);
    Assert.assertTrue(result);
  }

  @Test
  public void test1() {
    int[][] arr = {{1, 1}, {2, 2}, {3, 4}, {4, 5}, {5, 6}, {7, 7}};
    boolean result = checkStraightLine(arr);
    Assert.assertFalse(result);
  }

  @Test
  public void test2() {
    int[][] arr = {{-3,-2},{-1,-2},{2,-2},{-2,-2},{0,-2}};
    boolean result = checkStraightLine(arr);
    Assert.assertTrue(result);
  }
}
