package S501_600;

/**
 * Create by haifei on 4/12/2018 9:54 PM.
 * <p>
 * https://leetcode.com/problems/delete-operation-for-two-strings/
 * https://blog.csdn.net/qq_35082030/article/details/79973562
 */
public class S583_DeleteOperationforTwoStrings {

  // 使用二维矩阵纪录状态，进行解题
  public int minDistance(String word1, String word2) {
    if (word1 == null || word1.length() == 0) {
      return word2.length();
    }
    if (word2 == null || word2.length() == 0) {
      return word1.length();
    }

    if (word1.length() < word2.length()) {
      return minDistance(word2, word1);
    }

    int row = word2.length();
    int col = word1.length();
    int[][] dp = new int[row][col];
    int maxLen = 0;

    //初始化第一行
    for (int i = 0; i < col; i++) {
      if (word1.charAt(i) == word2.charAt(0)) {
        while (i < col) {
          dp[0][i++] = 1;
        }
        maxLen = 1;
      }
    }

    //初始化第一列
    for (int i = 0; i < row; i++) {
      if (word2.charAt(i) == word1.charAt(0)) {
        while (i < row) {
          dp[i++][0] = 1;
        }
        maxLen = 1;
      }
    }

    //根据第一行与第一列来解决问题
    for (int i = 1; i < row; i++) {
      for (int j = 1; j < col; j++) {

        int maxTemp = Math.max(dp[i - 1][j], dp[i][j - 1]);

        if (word1.charAt(j) == word2.charAt(i)) {
          dp[i][j] = Math.max(maxTemp, dp[i - 1][j - 1] + 1);
        } else {
          dp[i][j] = maxTemp;
        }

        if (dp[i][j] > maxLen) {
          maxLen = dp[i][j];
        }
      }
    }

    return row + col - 2 * maxLen;
  }

  //  对上面的时间以及空间进行优化
  public int minDistance2(String word1, String word2) {
    if (word1 == null || word1.length() == 0) {
      return word2.length();
    }
    if (word2 == null || word2.length() == 0) {
      return word1.length();
    }

    if (word1.length() < word2.length()) {
      return minDistance(word2, word1);
    }

    int row = word2.length();
    int col = word1.length();
    int[] dp = new int[col];
    int maxLen = 0;

    //初始化第一行
    for (int i = 0; i < col; i++) {
      if (word1.charAt(i) == word2.charAt(0)) {
        while (i < col) {
          dp[i++] = 1;
        }
        maxLen = 1;
        break;
      }
    }

    for (int i = 1; i < row; i++) {

      int[] temp = new int[col];

      for (int j = 0; j < col; j++) {
        //如果是首位的话
        if (j == 0) {
          temp[0] = word1.charAt(j) == word2.charAt(i) ? 1 : 0;
          temp[0] = Math.max(dp[0], temp[0]);
        } else {
          temp[j] = Math.max(temp[j - 1], dp[j]);
          if (word1.charAt(j) == word2.charAt(i)) {
            temp[j] = Math.max(temp[j], dp[j - 1] + 1);
          }
        }
        if (temp[j] > maxLen) {
          maxLen = temp[j];
        }
      }
      dp = temp;
    }

    return col + row - 2 * maxLen;
  }


  public static void main(String[] args) {
    final S583_DeleteOperationforTwoStrings operationforTwoStrings =
      new S583_DeleteOperationforTwoStrings();

    System.out.println(operationforTwoStrings.minDistance("a", "a"));
    System.out.println(operationforTwoStrings.minDistance("sea", "eat"));
    System.out.println(operationforTwoStrings.minDistance("", "a"));
    System.out.println(operationforTwoStrings.minDistance("mart", "karmt"));
    System.out.println(operationforTwoStrings.minDistance("a", "b"));
    System.out.println(operationforTwoStrings.minDistance("food", "money"));

    System.out.println("第二种方法");

    System.out.println(operationforTwoStrings.minDistance2("a", "a"));
    System.out.println(operationforTwoStrings.minDistance2("sea", "eat"));
    System.out.println(operationforTwoStrings.minDistance2("", "a"));
    System.out.println(operationforTwoStrings.minDistance2("mart", "karmt"));
    System.out.println(operationforTwoStrings.minDistance2("a", "b"));
    System.out.println(operationforTwoStrings.minDistance2("food", "money"));

  }
}
