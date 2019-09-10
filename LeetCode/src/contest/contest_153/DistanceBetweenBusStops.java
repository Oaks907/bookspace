package contest.contest_153;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 8/9/2019 10:37 AM.
 */
public class DistanceBetweenBusStops {

  public int distanceBetweenBusStops(int[] distance, int start, int destination) {

    if (null == distance || distance.length == 0) {
      return 0;
    }

    if (start > destination) {
      int temp = start;
      start = destination;
      destination = temp;
    }

    int length = distance.length;
    int leftToRightResult = 0;
    int rightToLeftResult = 0;

    // 由左到右
    for (int i = start; i < destination; i++) {
      leftToRightResult += distance[i];
    }

    // 由右到左
    for (int i = 0; i < start; i++) {
      rightToLeftResult += distance[i];
    }
    for (int i = destination; i < length; i++) {
      rightToLeftResult += distance[i];
    }

//    System.out.println("leftToRightResult:" + leftToRightResult + ",rightToLeftResult:" + rightToLeftResult);

    return Math.min(leftToRightResult, rightToLeftResult);
  }

  @Test
  public void test() {
    DistanceBetweenBusStops distanceBetweenBusStops = new DistanceBetweenBusStops();

    int[] distance = {1, 2, 3, 4};
    int result = distanceBetweenBusStops.distanceBetweenBusStops(distance, 0, 1);

    Assert.assertEquals(1, result);
  }

  @Test
  public void test2() {
    DistanceBetweenBusStops distanceBetweenBusStops = new DistanceBetweenBusStops();

    int[] distance = {1, 2, 3, 4};
    int result = distanceBetweenBusStops.distanceBetweenBusStops(distance, 0, 2);

    Assert.assertEquals(3, result);
  }

  @Test
  public void test3() {
    DistanceBetweenBusStops distanceBetweenBusStops = new DistanceBetweenBusStops();

    int[] distance = {1, 2, 3, 4};
    int result = distanceBetweenBusStops.distanceBetweenBusStops(distance, 0, 3);

    Assert.assertEquals(4, result);
  }

  @Test
  public void test4() {
    DistanceBetweenBusStops distanceBetweenBusStops = new DistanceBetweenBusStops();

    int[] distance = {8, 11, 6, 7, 10, 11, 2};
    int result = distanceBetweenBusStops.distanceBetweenBusStops(distance, 0, 3);

    Assert.assertEquals(25, result);
  }
}
