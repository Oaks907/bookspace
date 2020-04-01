package S1101_S1200;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 10/3/2020 8:46 AM.
 */
public class S1143_LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {

        if (text2.length() > text1.length()) {
            String tmp = text1;
            text1 = text2;
            text2 = tmp;
        }

        int len1 = text1.length();
        int len2 = text2.length();

        int[][] dp = new int[len2][len1];

        Character c = text2.charAt(0);
        dp[0][0] = c == text1.charAt(0) ? 1 : 0;
        for (int i = 1; i < len1; i++) {
            if (c == text1.charAt(i)) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = dp[0][i - 1];
            }
        }
        c = text1.charAt(0);
        for (int i = 1; i < len2; i++) {
            if (c == text2.charAt(i)) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }

        for (int i = 1; i < len2; i++) {

            Character c2 = text2.charAt(i);

            for (int j = 1; j < len1; j++) {

                char c1 = text1.charAt(j);

                if (c2 == c1) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

//        PrintUtils.printArray(dp);

        return dp[len2 - 1][len1 - 1];
    }

    @Test
    public void test4() {

        int result = longestCommonSubsequence("aaa", "aa");

        Assert.assertEquals(2, result);
    }

    @Test
    public void test3() {

        int result = longestCommonSubsequence("ezupkr", "ubmrapg");

        Assert.assertEquals(2, result);
    }

    @Test
    public void test() {

        int result = longestCommonSubsequence("abcde", "ace");

        Assert.assertEquals(3, result);
    }

    @Test
    public void test1() {

        int result = longestCommonSubsequence("abc", "abc");

        Assert.assertEquals(3, result);
    }

    @Test
    public void test2() {

        int result = longestCommonSubsequence("abc", "def");

        Assert.assertEquals(0, result);
    }
}
