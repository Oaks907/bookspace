package contest.contest_179;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 8/3/2020 11:15 AM.
 */
public class BulbSwitcherIII {

    public int numTimesAllBlue(int[] light) {

        if (null == light || 0 == light.length) {
            return 0;
        }

        int len = light.length;
        boolean[] blue = new boolean[light.length];
        int result = 0;
        int lastIndex = Integer.MIN_VALUE;

        for (int i = 0; i < len; i++) {
            int index = light[i] - 1;
            lastIndex = Math.max(index, lastIndex);
            blue[index] = true;
            if (checkIsBlueMoment(blue, lastIndex)) {
                result++;
            }
        }

        return result;
    }

    public boolean checkIsBlueMoment(boolean[] blue, int lastIndex) {
        for (int i = 0; i < lastIndex; i++) {
            if (!blue[i]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test1() {
        int[] arr = {1, 2, 3, 4, 5, 6};

        int result = numTimesAllBlue(arr);

        Assert.assertEquals(6, result);
    }

    @Test
    public void test2() {
        int[] arr = {2, 1, 3, 5, 4};

        int result = numTimesAllBlue(arr);

        Assert.assertEquals(3, result);
    }

    @Test
    public void test3() {
        int[] arr = {3, 2, 4, 1, 5};

        int result = numTimesAllBlue(arr);

        Assert.assertEquals(2, result);
    }

    @Test
    public void test4() {
        int[] arr = {4, 1, 2, 3};

        int result = numTimesAllBlue(arr);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test5() {
        int[] arr = {2, 1, 4, 3, 6, 5};

        int result = numTimesAllBlue(arr);

        Assert.assertEquals(3, result);
    }
}
