package contest.contest_197;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 12/7/2020 10:39 AM.
 */
public class NumSub {

    int MOD = 1000000000 + 7;

    public int numSub(String s) {
        String[] split = s.split("0");
        int result = 0;

        for (int i = 0; i < split.length; i++) {
            int n = split[i].length();
            result = (int) (((Math.pow(n, 2) % MOD + n) % MOD / 2 + result) % MOD);
        }

        return result;
    }

    @Test
    public void test() {

        int result = numSub("0110111");

        Assert.assertEquals(9, result);
    }

    @Test
    public void test1() {

        int result = numSub("101");

        Assert.assertEquals(2, result);
    }

    @Test
    public void test2() {

        int result = numSub("111111");

        Assert.assertEquals(21, result);
    }
}
