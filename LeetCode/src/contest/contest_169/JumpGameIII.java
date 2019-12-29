package contest.contest_169;

import org.junit.Test;

/**
 * Create by haifei on 29/12/2019 11:33 AM.
 */
public class JumpGameIII {


  public boolean canReach(int[] arr, int start) {
    boolean[] dp = new boolean[arr.length];
    return help(arr, start, dp);
  }


  private boolean help(int[] arr, int start, boolean[] dp) {

    if (0 > start || start >= arr.length) {
      return false;
    }

    if (0 == arr[start]) {
      return true;
    }

    if (dp[start]) {
      return false;
    }

    dp[start] = true;
    boolean left = help(arr, start + arr[start], dp);
    boolean right = help(arr, start - arr[start], dp);

    return left || right;
  }

  @Test
  public void test() {

    int[] arr = {4, 2, 3, 0, 3, 1, 2};

    System.out.println(canReach(arr, 4));
  }

  @Test
  public void test1() {

    int[] arr = {3, 0, 2, 1, 2};

    System.out.println(canReach(arr, 2));
  }


}
