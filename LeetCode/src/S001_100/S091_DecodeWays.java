package S001_100;

/**
 * Create by haifei on 11/12/2018 11:11 PM.
 */
public class S091_DecodeWays {

  public int numDecodings(String s) {
    if (null == s || s.length() == 0) {
      return 0;
    }

    final int length = s.length();
    int[] dp = new int[length + 1];
    dp[0] = 1;
    dp[1] = s.charAt(0) == '0' ? 0 : 1;

    for (int i = 2; i <= length; i++) {
      final int first = Integer.parseInt(s.substring(i - 1, i)); //不包括第 i 项
      final int second = Integer.parseInt(s.substring(i - 2, i));
      if (first <= 9 && first >= 1) {
        dp[i] += dp[i - 1];
      }
      if (second <= 26 && second >= 10) {
        dp[i] += dp[i - 2];
      }
    }
    return dp[length];
  }

  public static void main(String[] args) {
    final S091_DecodeWays decodeWays = new S091_DecodeWays();

    System.out.println(decodeWays.numDecodings("226"));
  }
}
