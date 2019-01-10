package S601_700;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Create by haifei on 9/1/2019 9:48 PM.
 * https://leetcode.com/problems/maximum-length-of-pair-chain/
 */
public class S646_MaximumLengthofPairChain {

  public int findLongestChain(int[][] pairs) {
    Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));

    int result = 1;
    int temp = pairs[0][1];
    for (int i = 1; i < pairs.length; i++) {
      if (pairs[i][0] > temp) {
        result++;
        temp = pairs[i][1];
      }
    }

    return result;
  }

  public static void main(String[] args) {
    int[][] pairs = {{1, 2}, {2, 3}, {3, 4}};

    final S646_MaximumLengthofPairChain pairChain =
      new S646_MaximumLengthofPairChain();
    System.out.println(pairChain.findLongestChain(pairs));
  }
}
