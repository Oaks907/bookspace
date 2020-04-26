package contest.contest_186;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 26/4/2020 9:23 PM.
 */
public class MaximumScoreAfterSplittingaString {

    public int maxScore(String s) {

        int length = s.length();
        int one = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '1') {
                one++;
            }
        }

        int res = 0;
        int left = 0;
        int right = one;

        for (int i = 0; i < length - 1; i++) {
            char c = s.charAt(i);

            if (c == '0') {
                left++;
            } else {
                right--;
            }

            res = Math.max(res, left + right);
        }

        return res;
    }

    @Test
    public void test() {

        int res = maxScore("011101");

        Assert.assertEquals(5, res);
    }
}
