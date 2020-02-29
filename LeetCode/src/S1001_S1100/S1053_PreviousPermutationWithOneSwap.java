package S1001_S1100;

import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 28/2/2020 12:42 PM.
 */
public class S1053_PreviousPermutationWithOneSwap {

    public int[] prevPermOpt1(int[] A) {
        if (null == A || 0 == A.length) {
            return A;
        }

        int idx = -1;
        for (int i = A.length - 1; i >= 1; i--) {
            if (A[i] < A[i - 1]) {
                idx = i - 1;
                break;
            }
        }

        if (-1 == idx) {
            return A;
        }

        for (int i = A.length - 1; i >= idx; i--) {
            if (A[i] < A[idx] && A[i] != A[i - 1]) {
                int temp = A[i];
                A[i] = A[idx];
                A[idx] = temp;
                return A;
            }
        }

        return A;
    }

    @Test
    public void test0() {
        int[] arr = {5, 3, 1, 1, 3};

        int[] result = prevPermOpt1(arr);

        // 5, 1, 3, 1, 3
        PrintUtils.printArray(result);
    }

    @Test
    public void test() {
        int[] arr = {3, 2, 1};

        int[] result = prevPermOpt1(arr);

        // 3 1 2
        PrintUtils.printArray(result);
    }

    @Test
    public void test1() {
        int[] arr = {1, 1, 5};

        int[] result = prevPermOpt1(arr);

        // 1 1 5
        PrintUtils.printArray(result);
    }

    @Test
    public void test2() {
        int[] arr = {1, 9, 4, 6, 7};

        int[] result = prevPermOpt1(arr);

        // 1, 7, 4, 6, 9,
        PrintUtils.printArray(result);
    }

    @Test
    public void test3() {
        int[] arr = {3, 1, 1, 3};

        int[] result = prevPermOpt1(arr);

        // 1, 3, 1, 3,
        PrintUtils.printArray(result);
    }
}
