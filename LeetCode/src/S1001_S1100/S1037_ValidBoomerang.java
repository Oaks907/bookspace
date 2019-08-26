package S1001_S1100;

/**
 * Create by haifei on 6/5/2019 8:53 AM.
 * <p>
 * https://leetcode.com/problems/valid-boomerang/
 */
public class S1037_ValidBoomerang {

  public boolean isBoomerang(int[][] points) {
    int x1 = points[0][0];
    int y1 = points[0][1];
    int x2 = points[1][0];
    int y2 = points[1][1];
    int x3 = points[2][0];
    int y3 = points[2][1];

    //存在相同点，直接返回false
    if ((y1 == y2 && x1 == x2) || (y1 == y3 && x1 == x3) || (y2 == y3 && x2 == x3)) {
      return false;
    }

    if (x1 - x2 == 0) {
      if (x2 - x3 == 0) {
        return false;
      }
      return true;
    } else if (x1 - x3 == 0) {
      if (x2 - x3 == 0) {
        return false;
      }
      return true;
    } else if (x2 - x3 == 0) {
      return true;
    }

    if ((double) (y1 - y2) / (x1 - x2) != (double) (y1 - y3) / (x1 - x3))
      return true;
    return false;
  }
}
