package S701_800;

import org.junit.Assert;
import org.junit.Test;

import java.util.TreeMap;

/**
 * https://leetcode.com/problems/my-calendar-ii/
 * Create by haifei on 18/10/2019 8:54 AM.
 */
public class S731_MyCalendarII {

  class Interval {
    int start, end;

    Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  TreeMap<Integer, Interval> intersection = new TreeMap<>();
  TreeMap<Integer, Interval> map = new TreeMap<>();

  public S731_MyCalendarII() {

  }

  public boolean book(int start, int end) {
    if (checkIntersection(start, end)) {
      return false;
    }
    if (map.size() == 0) {
      map.put(start, new Interval(start, end));
      return true;
    }

    Integer preKey = map.floorKey(start);

    if (preKey != null && map.get(preKey).end > start) {
      intersection.put(start, new Interval(start, Math.min(end, map.get(preKey).end)));
      start = map.get(preKey).start;
      end = Math.max(end, map.get(preKey).end);
      map.remove(preKey);
    }

    Integer nextKey = map.higherKey(start);
    while (nextKey != null && nextKey < end) {

      intersection.put(nextKey, new Interval(nextKey, Math.min(end, map.get(nextKey).end)));
      end = Math.max(end, map.get(nextKey).end);
      map.remove(nextKey);

      nextKey = map.higherKey(start);
    }

    map.put(start, new Interval(start, end));
    return true;
  }

  public boolean checkIntersection(int start, int end) {
    Integer preKey = intersection.floorKey(start);
    if (preKey != null && intersection.get(preKey).end > start) {
      return true;
    }
    Integer nextKey = intersection.ceilingKey(start);
    if (nextKey != null && intersection.get(nextKey).start < end) {
      return true;
    }
    return false;

  }

  @Test
  public void test1() {
    S731_MyCalendarII calendarII = new S731_MyCalendarII();
    Assert.assertTrue(calendarII.book(10, 20));
    Assert.assertTrue(calendarII.book(50, 60));
    Assert.assertTrue(calendarII.book(10, 40));
    Assert.assertFalse(calendarII.book(5, 15));
    Assert.assertTrue(calendarII.book(5, 10));
    Assert.assertTrue(calendarII.book(25, 55));
  }

  @Test
  public void test2() {
    S731_MyCalendarII calendarII = new S731_MyCalendarII();
    Assert.assertTrue(calendarII.book(24, 40));
    Assert.assertTrue(calendarII.book(43, 50));
    Assert.assertTrue(calendarII.book(27, 43));
    Assert.assertTrue(calendarII.book(5, 21));
    Assert.assertFalse(calendarII.book(30, 40));
    Assert.assertFalse(calendarII.book(14, 29));
    Assert.assertTrue(calendarII.book(3, 19));
    Assert.assertFalse(calendarII.book(3, 14));
    Assert.assertFalse(calendarII.book(25, 39));
    Assert.assertFalse(calendarII.book(6, 1));
  }
}
