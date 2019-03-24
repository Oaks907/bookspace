package S1001_S1200;

import org.junit.Test;

/**
 * Create by haifei on 24/3/2019 10:01 AM.
 * <p>
 * https://leetcode.com/contest/weekly-contest-129/problems/best-sightseeing-pair/
 */
public class S1021_BestSightseeingPair {

  public int maxScoreSightseeingPair(int[] A) {

    if (A == null || A.length == 0) {
      return 0;
    }

    int max_so_far = A[0];
    int max = 0;

    for (int i = 1; i < A.length; i++) {
      max = Math.max(max, max_so_far + A[i] - i);
      max_so_far = Math.max(max_so_far, A[i] + i);
    }

    return max;
  }

  @Test
  public void test() {
    int[] A = {8, 1, 5, 2, 6};

    final S1021_BestSightseeingPair sightseeingPair = new S1021_BestSightseeingPair();
    System.out.println(sightseeingPair.maxScoreSightseeingPair(A));
  }
}
