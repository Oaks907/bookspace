package contest.contest_154;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 15/9/2019 10:33 AM.
 */
public class MaximumNumberofBalloons {

  public int maxNumberOfBalloons(String text) {

    if (text == null || text.isEmpty()) {
      return 0;
    }

    String balloon = "balloon";
    String[] splitBalloon = balloon.split("");

    int result = 0;

    while (true) {
      for (int i = 0; i < splitBalloon.length; i++) {
        if (text.contains(splitBalloon[i])) {
          text = text.replaceFirst(splitBalloon[i], "");
        } else {
          return result;
        }
      }
      result++;
    }
  }

  @Test
  public void test() {
    MaximumNumberofBalloons numberofBalloons = new MaximumNumberofBalloons();
    int result = numberofBalloons.maxNumberOfBalloons("nlaebolko");
    Assert.assertEquals(1, result);
  }

  @Test
  public void test1() {
    MaximumNumberofBalloons numberofBalloons = new MaximumNumberofBalloons();
    int result = numberofBalloons.maxNumberOfBalloons("loonbalxballpoon");
    Assert.assertEquals(2, result);
  }

  @Test
  public void test2() {
    MaximumNumberofBalloons numberofBalloons = new MaximumNumberofBalloons();
    int result = numberofBalloons.maxNumberOfBalloons("leetcode");
    Assert.assertEquals(0, result);
  }

}
