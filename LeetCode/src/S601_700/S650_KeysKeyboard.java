package S601_700;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 16/3/2020 6:07 PM.
 */
public class S650_KeysKeyboard {

    public int minSteps(int n) {
        if (n == 0) {
            return 0;
        }

        int[] dp = new int[n + 1];

        for (int i = 2; i < n + 1; i++) {
            dp[i] = i;
            for (int j = i - 1; j > 1; j--) {
                if (i % j == 0) {
                    dp[i] = dp[j] + i / j;
                    break;
                }
            }
        }

        return dp[n];
    }

    @Test
    public void test2() {

        int result = minSteps(9);

        Assert.assertEquals(6, result);
    }

    @Test
    public void test() {

        int result = minSteps(3);

        Assert.assertEquals(3, result);
    }
}
