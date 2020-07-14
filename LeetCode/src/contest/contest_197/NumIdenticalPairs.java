package contest.contest_197;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 12/7/2020 10:32 AM.
 */
public class NumIdenticalPairs {

    public int numIdenticalPairs(int[] nums) {
        int[] count = new int[101];

        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }

        int result = 0;

        for (int i = 0; i < count.length; i++) {
            if (count[i] > 1) {
                result += count[i] * (count[i] - 1) / 2;
            }
        }

        return result;
    }

    @Test
    public void test() {
        int[] arr = {1, 2, 3, 1, 1, 3};
        int result = numIdenticalPairs(arr);

        Assert.assertEquals(4, result);
    }

    @Test
    public void test1() {
        int[] arr = {1, 1, 1, 1};
        int result = numIdenticalPairs(arr);

        Assert.assertEquals(6, result);
    }

    @Test
    public void test2() {
        int[] arr = {1, 2, 3};
        int result = numIdenticalPairs(arr);

        Assert.assertEquals(0, result);
    }
}

