package contest.contest_218;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 6/12/2020 11:04 AM.
 */
public class M_ConcatenationofConsecutiveBinaryNumbers {

    public int concatenatedBinary(int n) {
        int MOD = 1000000000 + 7;
        int result = 0;
        int shift = 0;

        for (int i = 1; i <= n; i++) {
            if ((((i - 1) & i) == 0)) {
                ++shift;
            }
            result = (int) ((((long) result << shift) + i) % MOD);
        }

        return result;
    }

    @Test
    public void test() {
        int result = concatenatedBinary(3);
        Assert.assertEquals(27, result);
    }

    @Test
    public void test1() {
        int result = concatenatedBinary(12);

        Assert.assertEquals(505379714, result);
    }
}
