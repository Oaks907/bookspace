package contest.contest_188;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 10/5/2020 11:14 AM.
 */
public class CountTripletsThatCanFormTwoArraysofEqualXOR {

    public int countTriplets(int[] arr) {

        int n = arr.length + 1, res = 0, prefix[] = new int[n];

        for (int i = 1; i < n; i++) {
            prefix[i] = arr[i - 1] ^ prefix[i - 1];
        }

//        PrintUtils.printArray(prefix);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (prefix[i] == prefix[j]) {
                    res += j - i - 1;
                }
            }
        }

        return res;
    }

    @Test
    public void test() {

        int[] arr = {2, 3, 1, 6, 7};

        int result = countTriplets(arr);

        Assert.assertEquals(4, result);
    }
}
