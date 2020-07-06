package contest.contest_196;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 5/7/2020 10:34 AM.
 */
public class GetLastMoment {

    public int getLastMoment(int n, int[] left, int[] right) {

        int max = -1;

        for (int i = 0; i < left.length; i++) {
            max = Math.max(max, left[i]);
        }

        for (int i = 0; i < right.length; i++) {
            max = Math.max(max, n - right[i]);
        }

        return max;
    }

    @Test
    public void test5() {
        int left[] = {5};
        int right[] = {4};

        int result = getLastMoment(9, left, right);

        Assert.assertEquals(5, result);
    }

    @Test
    public void test() {
        int left[] = {4, 3};
        int right[] = {0, 1};

        int result = getLastMoment(4, left, right);

        Assert.assertEquals(4, result);
    }

    @Test
    public void test1() {
        int left[] = {};
        int right[] = {0, 1, 2, 3, 4, 5, 6, 7};

        int result = getLastMoment(7, left, right);

        Assert.assertEquals(7, result);
    }

    @Test
    public void test2() {
        int left[] = {0, 1, 2, 3, 4, 5, 6, 7};
        int right[] = {};

        int result = getLastMoment(7, left, right);

        Assert.assertEquals(7, result);
    }

    @Test
    public void test3() {
        int left[] = {5};
        int right[] = {4};

        int result = getLastMoment(5, left, right);

        Assert.assertEquals(5, result);
    }

    @Test
    public void test4() {
        int left[] = {6};
        int right[] = {0};

        int result = getLastMoment(6, left, right);

        Assert.assertEquals(6, result);
    }
}
