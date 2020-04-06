package contest.contest_183;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 5/4/2020 11:17 AM.
 */
public class NumberofStepstoReduceaNumberinBinaryRepresentationtoOne {

    public int numSteps(String s) {

        int res = 0;
        int carry = 0;

        for (int i = s.length() - 1; i > 0; --i) {
            ++res;
            if (s.charAt(i) - '0' + carry == 1) {
                carry = 1;
                ++res;
            }
        }
        return res + carry;
    }

    @Test
    public void test() {

        int result = numSteps("1101");
        Assert.assertEquals(6, result);
    }

    @Test
    public void test1() {

        int result = numSteps("1111011110000011100000110001011011110010111001010111110001");
        Assert.assertEquals(85, result);
    }

}
