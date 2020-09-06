package bweekly.contest_34;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 5/9/2020 10:48 PM.
 */
public class ShortestSubarraytobeRemovedtoMakeArraySorted {

    public int findLengthOfShortestSubarray(int[] arr) {

        int left = -1;
        int right = arr.length - 1;

        int len = arr.length;

        for (int i = 1; i < len; i++) {
            if (arr[i] < arr[i - 1]) {
                left = i - 1;
                break;
            }
        }

        if (left == -1) {
            return 0;
        }

        for (int i = len - 1; i > left; i--) {
            if (arr[i] < arr[i - 1]) {
                right = i;
                break;
            }
        }

        int result = Math.min(len - left - 1, right);

        int i = 0, j = right;
        while (i <= left && j < len) {
            if (arr[i] <= arr[j]) {
                result = Math.min(result, j - i - 1);
                i++;
            } else {
                j++;
            }
        }

        return result;
    }

    @Test
    public void test() {

        int[] arr = {1, 2, 3, 10, 4, 2, 3, 5};

        int result = findLengthOfShortestSubarray(arr);

        Assert.assertEquals(3, result);
    }

    @Test
    public void test1() {

        int[] arr = {5, 4, 3, 2, 1};

        int result = findLengthOfShortestSubarray(arr);

        Assert.assertEquals(4, result);
    }
}