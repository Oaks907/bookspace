package S701_800;

/**
 * Create by haifei on 10/12/2018 7:21 PM.
 * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
 */
public class S712_MinimumASCIIDeleteSumforTwoStringsMedium {

  public int minimumDeleteSum(String s1, String s2) {

    if (s1.length() < s2.length()) {
      return minimumDeleteSum(s2, s1);
    }

    int row = s2.length();  //行
    int col = s1.length();

    int[][] dp = new int[row + 1][col + 1];

    //初始化第一行
    for (int i = 1; i <= col; i++) {
      dp[0][i] = dp[0][i - 1] + s1.charAt(i - 1);
    }

    //初始化第一列
    for (int i = 1; i <= row; i++) {
      dp[i][0] = dp[i - 1][0] + s2.charAt(i - 1);
    }

    for (int i = 1; i <= row; i++) {
      for (int j = 1; j <= col; j++) {
        if (s2.charAt(i - 1) == s1.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.min(dp[i - 1][j] + s2.charAt(i - 1), dp[i][j - 1] + s1.charAt(j - 1));
        }
      }
    }
    return dp[row][col];
  }

  public static void main(String[] args) {
    final S712_MinimumASCIIDeleteSumforTwoStringsMedium medium = new S712_MinimumASCIIDeleteSumforTwoStringsMedium();
    System.out.println(medium.minimumDeleteSum("sea", "eat"));
    System.out.println(Character.getNumericValue('e'));
  }
}
