package S801_S900;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Create by haifei on 27/3/2019 8:57 AM.
 * https://leetcode.com/problems/maximize-distance-to-closest-person/
 */
public class S849_MaximizeDistancetoClosestPerson {

  public int maxDistToClosest(int[] seats) {

    if (null == seats || seats.length == 0) {
      return 0;
    }

    int left = 0;
    int right = 0;
    int maxDistance = Integer.MIN_VALUE;

    for (int i = 0; i < seats.length; i++) {
      if (seats[i] == 1) {

        if (left == 0) {
          if (right > maxDistance) {
            maxDistance = right;
          }
        } else {
          int curDistance = ((right - left) >> 1) + ((right - left) % 2 == 0 ? 0 : 1);
          if (curDistance > maxDistance) {
            maxDistance = curDistance;
          }
        }

        left = i + 1;
        right = i + 1;
      } else {
        right++;
      }
    }

    if (seats[right - 1] == 0 && (right - left) > maxDistance) {
      maxDistance = right - left;
    }

    return maxDistance;
  }

  S849_MaximizeDistancetoClosestPerson maximizeDistancetoClosestPerson = null;

  @Before
  public void before() {
    maximizeDistancetoClosestPerson = new S849_MaximizeDistancetoClosestPerson();
  }

  @Test
  public void test4() {
    int[] A = {1, 0, 0, 1,};
    final int value = maximizeDistancetoClosestPerson.maxDistToClosest(A);
    Assert.assertEquals(1, value);
  }

  @Test
  public void test3() {
    int[] A = {0, 0, 0, 1,};
    final int value = maximizeDistancetoClosestPerson.maxDistToClosest(A);
    Assert.assertEquals(3, value);
  }

  @Test
  public void test2() {
    int[] A = {1, 0, 0, 0};
    final int value = maximizeDistancetoClosestPerson.maxDistToClosest(A);
    Assert.assertEquals(3, value);
  }

  @Test
  public void test1() {
    int[] A = {1, 0, 0, 0, 1, 0, 1};
    final int value = maximizeDistancetoClosestPerson.maxDistToClosest(A);
    Assert.assertEquals(2, value);
  }
}
