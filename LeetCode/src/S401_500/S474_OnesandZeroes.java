package S401_500;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 21/5/2020 12:56 PM.
 */
public class S474_OnesandZeroes {

    private int[][][] dpTable;

    public int findMaxForm(String[] strs, int m, int n) {
        dpTable = new int[m + 1][n + 1][strs.length];
        return helper(strs, 0, m, n);
    }

    private int helper(String[] strs, int index, int m, int n) {
        if (index >= strs.length || m + n == 0) {
            return 0;
        }

        if (dpTable[m][n][index] != 0) {
            return dpTable[m][n][index];
        }

        String curStr = strs[index];
        int zeroNum = countZero(curStr);
        int oneNum = curStr.length() - zeroNum;
        int countAddNum = 0;
        int countSkipNum;
        if (m >= zeroNum && n >= oneNum) {
            countAddNum = 1 + helper(strs, index + 1, m - zeroNum, n - oneNum);
        }
        countSkipNum = helper(strs, index + 1, m, n);

        dpTable[m][n][index] = Math.max(countAddNum, countSkipNum);

        return Math.max(countAddNum, countSkipNum);
    }

    private int countZero(String str) {

        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if ('0' == str.charAt(i)) {
                count++;
            }
        }

        return count;
    }

    @Test
    public void test() {
        String[] str = {"10", "0001", "111001", "1", "0"};
        int result = findMaxForm(str, 5, 3);
        Assert.assertEquals(4, result);
    }
}
