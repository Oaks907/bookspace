package contest.contest_184;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 12/4/2020 11:21 AM.
 */
public class NumberofWaystoPaintN3Grid {

    public int numOfWays(int n) {
        long a121 = 6, a123 = 6, b121, b123, mod = (long)1e9 + 7;
        for (int i = 1; i < n; ++i) {
            b121 = a121 * 3 + a123 * 2;
            b123 = a121 * 2 + a123 * 2;
            a121 = b121 % mod;
            a123 = b123 % mod;
        }
        return (int)((a121 + a123) % mod);
    }

    @Test
    public void test() {
        int result = numOfWays(7);

        Assert.assertEquals(106494, result);
    }

    @Test
    public void test3() {
        int result = numOfWays(2);

        Assert.assertEquals(54, result);
    }

    @Test
    public void test1() {
        int result = numOfWays(5000);

        Assert.assertEquals(30228214, result);
    }
}
