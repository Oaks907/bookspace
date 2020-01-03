package S1201_S1300;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 31/12/2019 6:36 PM.
 */
public class S1300_SumOfMutatedArrayClosesttoTarget {

  public int findBestValue(int[] arr, int target) {

    int max = Integer.MIN_VALUE;
    int sum = 0;

    for (int item : arr) {
      sum += item;
      max = Math.max(max, item);
    }

    if (sum == target) {
      return max;
    }

    int min = 0;
    int res = 0;
    int diff = Integer.MAX_VALUE;

    while (min <= max) {
      int mid = min + (max - min) / 2;
      int value = getMutatedSum(arr, mid);

      if (value > target) {
        max = mid - 1;
      } else {
        min = mid + 1;
      }

      if (Math.abs(value - target) < diff || (Math.abs(value - target) == diff && mid < res)) {
        res = mid;
        diff = Math.abs(value - target);
      }
    }

    return res;
  }

  private int getMutatedSum(int[] arr, int mid) {
    int sum = 0;
    for (int i : arr) {
      sum += Math.min(i, mid);
    }

    return sum;
  }

  @Test
  public void test4() {

    int[] arr = {60864, 25176, 27249, 21296, 20204};

    int result = findBestValue(arr, 56803);

    Assert.assertEquals(11361, result);
  }

  @Test
  public void test3() {

    int[] arr = {48772, 52931, 14253, 32289, 75263};

    int result = findBestValue(arr, 40876);

    Assert.assertEquals(8175, result);
  }

  @Test
  public void test() {

    int[] arr = {4, 9, 3};

    int result = findBestValue(arr, 10);

    Assert.assertEquals(3, result);
  }

  @Test
  public void test2() {

    int[] arr = {2, 3, 5};

    int result = findBestValue(arr, 10);

    Assert.assertEquals(5, result);
  }

  @Test
  public void test1() {

    int[] arr = {60864, 25176, 27249, 21296, 20204};

    int result = findBestValue(arr, 56803);

    Assert.assertEquals(11361, result);
  }

}
