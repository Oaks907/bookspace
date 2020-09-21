package bweekly.contest_35;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 19/9/2020 10:31 PM.
 */
public class SumofAllOddLengthSubarrays {

    public int sumOddLengthSubarrays(int[] arr) {

        int len = arr.length;

        int[] sum = new int[len + 1];
        sum[1] = arr[0];
        for (int i = 1; i < len; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }

        int result = 0;
        for (int i = 0; i < len; i++) {
            result += getValue(arr, i, sum);
        }

        return result;
    }

    public int getValue(int[] arr, int index, int[] sum) {
        int len = arr.length;
        int val = 0;
        for (int i = index; i < len; ) {
            val += sum[i + 1] - sum[index];
            i = i + 2;
        }

        return val;
    }

    @Test
    public void test() {
        int[] arr = {1, 4, 2, 5, 3};

        int result = sumOddLengthSubarrays(arr);

        Assert.assertEquals(58, result);
    }

    @Test
    public void test1() {
        int[] arr = {1, 2};

        int result = sumOddLengthSubarrays(arr);

        Assert.assertEquals(3, result);
    }

    @Test
    public void test2() {
        int[] arr = {10, 11, 12};

        int result = sumOddLengthSubarrays(arr);

        Assert.assertEquals(66, result);
    }

}
