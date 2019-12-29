package contest.contest_169;

import org.junit.Test;
import utils.PrintUtils;

/**
 * Create by haifei on 29/12/2019 10:56 AM.
 */
public class FindNUniqueIntegersSumuptoZero {

  public int[] sumZero(int n) {

    int[] result = new int[n];

    int left = 0;
    int right = n - 1;

    int val = n / 2;

    while (left < right) {
      result[left++] = -val;
      result[right--] = val;

      val--;
    }

    return result;
  }

  @Test
  public void test() {
    PrintUtils.printArray(sumZero(5));
    PrintUtils.printArray(sumZero(4));
  }
}
