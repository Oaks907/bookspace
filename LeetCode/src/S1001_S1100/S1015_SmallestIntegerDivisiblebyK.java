package S1001_S1100;

/**
 * Create by haifei on 24/3/2019 10:23 AM.
 *
 * https://leetcode.com/problems/smallest-integer-divisible-by-k/
 *
 */
public class S1015_SmallestIntegerDivisiblebyK {

  public int smallestRepunitDivByK(int K) {

    if (K % 2 == 0 || K % 5 == 0) {
      return -1;
    }

    int r = 0;

    for (int N = 1; N <= K; N++) {
      r = (r * 10 + 1) % K;
      if (r == 0) {
        return N;
      }
    }

    return -1;
  }
}
