package contest.contest_153;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 8/9/2019 11:12 AM.
 */
public class DayoftheWeek {

  // W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400) mod 7
  public String dayOfTheWeek(int day, int month, int year) {

    if ((month == 1) || (month == 2))//如果是一月或二月进行换算
    {
      month += 12;
      year--;
    }

    int result =
      (day + 2 * month + 3 * (month + 1) / 5 + year + year / 4 - year / 100 + year / 400) % 7;

    String[] desc = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    return desc[result];
  }

  @Test
  public void test1() {
    DayoftheWeek dayoftheWeek = new DayoftheWeek();

    String s = dayoftheWeek.dayOfTheWeek(31, 8, 2019);
    Assert.assertEquals("Saturday", s);
  }

  @Test
  public void test2() {
    DayoftheWeek dayoftheWeek = new DayoftheWeek();

    String s = dayoftheWeek.dayOfTheWeek(18, 7, 1999);
    Assert.assertEquals("Sunday", s);
  }

  @Test
  public void test3() {
    DayoftheWeek dayoftheWeek = new DayoftheWeek();

    String s = dayoftheWeek.dayOfTheWeek(15, 8, 1993);
    Assert.assertEquals("Sunday", s);
  }

  @Test
  public void test4() {
    DayoftheWeek dayoftheWeek = new DayoftheWeek();

    String s = dayoftheWeek.dayOfTheWeek(29, 2, 2016);
    Assert.assertEquals("Monday", s);
  }
}
