package S901_S1000;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 28/2/2020 1:43 AM.
 */
public class S915_PartitionArrayintoDisjointIntervals {

    public int partitionDisjoint(int[] A) {

        int leftMax = A[0];
        int max = leftMax;
        int index = 0;

        for (int i = 1; i < A.length; i++) {
            if (leftMax > A[i]) {
                leftMax = max;
                index = i;
            } else {
                max = Math.max(max, A[i]);
            }
        }

        return index + 1;
    }

    @Test
    public void test4() {

        int[] arr = {32, 57, 24, 19, 0, 24, 49, 67, 87, 87};

        int result = partitionDisjoint(arr);

        Assert.assertEquals(7, result);
    }

    @Test
    public void test3() {

        int[] arr = {26, 51, 40, 58, 42, 76, 30, 48, 79, 91};

        int result = partitionDisjoint(arr);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test2() {

        int[] arr = {1, 1};

        int result = partitionDisjoint(arr);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test() {

        int[] arr = {5, 0, 3, 8, 6};

        int result = partitionDisjoint(arr);

        Assert.assertEquals(3, result);
    }

    @Test
    public void test1() {

        int[] arr = {1, 1, 1, 0, 6, 12};

        int result = partitionDisjoint(arr);

        Assert.assertEquals(4, result);
    }
}
