package S1001_S1100;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/height-checker/
 * Create by haifei on 5/10/2019 6:36 AM.
 */
public class S1051_HeightChecker {

  public int heightChecker(int[] heights) {
    int[] copyArray = new int[heights.length];

    // copy
    for (int i = 0; i < heights.length; i++) {
      copyArray[i] = heights[i];
    }

    Arrays.sort(copyArray);

    int res = 0;
    for (int i = 0; i < heights.length; i++) {
      if (heights[i] != copyArray[i]) {
        res++;
      }
    }

    return res;
  }

  @Test
  public void test() {
    int[] A = {1, 1, 4, 2, 1, 3};

    int result = heightChecker(A);

    Assert.assertEquals(3, result);
  }

}
