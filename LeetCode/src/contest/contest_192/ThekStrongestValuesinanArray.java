package contest.contest_192;

import java.util.Arrays;

import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 7/6/2020.
 */

public class ThekStrongestValuesinanArray {

    public int[] getStrongest(int[] arr, int k) {
        if (k >= arr.length) {
            return arr;
        }

        int mIndex = (arr.length - 1) / 2;
        Arrays.sort(arr);
        int mVal = arr[mIndex];

        int[] strongArr = new int[arr.length];
        int left = 0;
        int right = arr.length - 1;
        int index = 0;

        while (left < right) {
            int leftVal = Math.abs(arr[left] - mVal);
            int rightVal = Math.abs(arr[right] - mVal);

            if (leftVal == rightVal) {
                strongArr[index] = arr[right--];
            } else {
                strongArr[index] = leftVal > rightVal ? arr[left++] : arr[right--];
            }
            index++;
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = strongArr[i];
        }

        return result;
    }

    @Test
    public void test() {
        int[] arr = {6, 7, 11, 7, 6, 8};

        int[] result = getStrongest(arr, 5);

        PrintUtils.printArray(result);
    }
}
