package contest.contest_187;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 3/5/2020 10:50 AM.
 */
public class AtLeastLengthKPlacesAway {

    public boolean kLengthApart(int[] nums, int k) {
        if (null == nums || 0 == nums.length) {
            return false;
        }

        int zeroCount = k;
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (val == 1) {
                if (zeroCount < k) {
                    return false;
                } else {
                    zeroCount = 0;
                }
            } else {
                zeroCount++;
            }
        }

        return true;
    }

    @Test
    public void test() {

        int[] res = {1, 0, 0, 0, 1, 0, 0, 1};

        boolean result = kLengthApart(res, 2);

        Assert.assertTrue(result);
    }

    @Test
    public void test1() {

        int[] res = {1, 0, 0, 1, 0, 1};

        boolean result = kLengthApart(res, 2);

        Assert.assertFalse(result);
    }

}
