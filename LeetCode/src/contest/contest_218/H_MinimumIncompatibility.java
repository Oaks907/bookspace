package contest.contest_218;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 6/12/2020 11:28 AM.
 */
public class H_MinimumIncompatibility {

    public int minimumIncompatibility(int[] nums, int k) {
        return 0;
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 1, 4};
        int result = minimumIncompatibility(nums, 2);

        Assert.assertEquals(4, result);
    }

    @Test
    public void test1() {
        int[] nums = {6, 3, 8, 1, 3, 1, 2,};
        int result = minimumIncompatibility(nums, 4);

        Assert.assertEquals(6, result);
    }

    @Test
    public void test2() {
        int[] nums = {5, 3, 3, 6, 3, 3};
        int result = minimumIncompatibility(nums, 3);

        Assert.assertEquals(-1, result);
    }
}
