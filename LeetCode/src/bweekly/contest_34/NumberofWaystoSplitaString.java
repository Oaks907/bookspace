package bweekly.contest_34;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 5/9/2020 10:51 PM.
 */
public class NumberofWaystoSplitaString {

    public int numWays(String s) {

        if (null == s || s.length() == 0) {
            return 0;
        }

        char[] chars = s.toCharArray();

        int oneCount = 0;
        long len = chars.length;
        int MOD = 1000000000 + 7;

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (chars[i] == '1') {
                oneCount++;
                list.add(i % MOD);
            }
        }
        if (oneCount == 0) {
            long n = len - 1;
            long ans = ((n * (n - 1)) % MOD / 2) % MOD;
            return Math.toIntExact(ans);
        } else if (oneCount % 3 != 0) {
            return 0;
        }

        int splitOne = oneCount / 3;

        long first = list.get(splitOne) - list.get(splitOne - 1);
        long second = list.get(list.size() - splitOne) - list.get(list.size() - splitOne - 1);

        return Math.toIntExact(((first % MOD) * (second % MOD)) % MOD);
    }

    @Test
    public void test() {

        int res = numWays("10101");

        Assert.assertEquals(4, res);
    }

    @Test
    public void test1() {

        int res = numWays("1001");

        Assert.assertEquals(0, res);
    }

    @Test
    public void test2() {

        int res = numWays("0000");

        Assert.assertEquals(3, res);
    }

    @Test
    public void test3() {

        int res = numWays("100100010100110");

        Assert.assertEquals(12, res);
    }
}
