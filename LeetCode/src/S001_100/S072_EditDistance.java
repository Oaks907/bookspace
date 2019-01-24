package S001_100;

import java.util.Arrays;

/**
 * Create by haifei on 22/1/2019 12:29 PM.
 */
public class S072_EditDistance {

  /**
   * 递归方法
   * 时间复杂度：
   * 空间复杂度：
   *
   * @param word1
   * @param word2
   * @return
   */
  public int minDistance_recursion(String word1, String word2) {
    int[][] saved = new int[word1.length()][word2.length()];

    for (int i = 0; i < word1.length(); i++) {
      Arrays.fill(saved[i], -1);
    }

    return min(word1.toCharArray(), word2.toCharArray(), 0, 0, 0, saved);
  }

  public int min(char[] word1, char[] word2, int s1, int s2, int cur, int[][] saved) {
    int min;

    if (s2 == word2.length) {
      return cur + word1.length - s1; //当前需要修改的次数 + word1剩余的数量
    }
    if (s1 == word1.length) {
      return cur + word2.length - s2; //当前需要修改的次数 + word2剩余的数量
    }

    //
    if (saved[s1][s2] >= 0) {
      return saved[s1][s2] + cur; //由当前变化到最后需要的次数 + 由一开始变化到现在需要的次数
    }

    if (word1[s1] == word2[s2]) {
      min = min(word1, word2, s1 + 1, s2 + 1, cur, saved);
    } else {

      //delete
      int delete = min(word1, word2, s1 + 1, s2, cur + 1, saved);
      //replace
      int replace = min(word1, word2, s1 + 1, s2 + 1, cur + 1, saved);
      //insert
      int insert = min(word1, word2, s1, s2 + 1, cur + 1, saved);

      min = Math.min(delete, Math.min(replace, insert));
    }

    //cur 记录使得初始的字符串变成现在的字符串所需的最少变化
    //saved 保存从当前位置的两个子字符串消去所需的最少变化。
    saved[s1][s2] = min - cur;

    return min;
  }

  public int minDistance_dynamic(String word1, String word2) {

    int word1Length = word1.length();
    int word2Length = word2.length();

    int dp[][] = new int[word1.length() + 1][word2.length() + 1];

    for (int i = 0; i <= word2Length; i++) {
      dp[0][i] = i;
    }
    for (int i = 0; i <= word1Length; i++) {
      dp[i][0] = i;
    }

    char[] word1Char = word1.toCharArray();
    char[] word2Char = word2.toCharArray();

    for (int i = 1; i <= word1Length; i++) {
      for (int j = 1; j <= word2Length; j++) {

        int min = Integer.MAX_VALUE;

        if (word1Char[i - 1] == word2Char[j - 1]) {
          min = dp[i - 1][j - 1];
        }

        int delete = 1 + dp[i][j - 1];
        int insert = 1 + dp[i - 1][j];
        int update = 1 + dp[i - 1][j - 1];

        dp[i][j] = Math.min(Math.min(min, delete), Math.min(insert, update));
      }
    }

    return dp[word1Length][word2Length];
  }

  public static void main(String[] args) {
    final S072_EditDistance editDistance = new S072_EditDistance();

    String str1 = "abc";
    String str2 = "bd";
    System.out.println(editDistance.minDistance_recursion(str1, str2));
//    System.out.println(editDistance.minDistance_dynamic(str1, str2));

    System.out.println(editDistance.minDistance_dynamic("", "a"));


  }
}
