package contest.contest_182;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 29/3/2020 10:33 AM.
 */
public class FindLuckyIntegerinanArray {

    public int findLucky(int[] arr) {

        int[] temp = new int[501];

        for (int i = 0; i < arr.length; i++) {
            temp[arr[i]] = temp[arr[i]] + 1;
        }

        int result = -1;
        for (int i = 1; i < temp.length; i++) {
            if (temp[i] == i) {
                result = i;
            }
        }
        return result;
    }

    @Test
    public void test() {

        int[] arr = {2, 2, 3, 4};

        int result = findLucky(arr);

        Assert.assertEquals(2, result);
    }
}
