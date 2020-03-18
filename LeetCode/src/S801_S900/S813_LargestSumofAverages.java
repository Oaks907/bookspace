package S801_S900;

import org.junit.Test;

import utils.PrintUtils;

/**
 * https://leetcode.com/problems/largest-sum-of-averages/
 * Create by haifei on 16/3/2020 5:52 PM.
 */
public class S813_LargestSumofAverages {

    public double largestSumOfAverages(int[] A, int K) {

        int N = A.length;
        double[][] memo = new double[N + 1][N + 1];

        double cur = 0;

        for (int i = 0; i < N; i++) {
            cur += A[i];
            memo[i + 1][1] = cur / (i + 1);
        }

        PrintUtils.printArray(memo);
        System.out.println();

        double search = search(N, K, A, memo);

        PrintUtils.printArray(memo);

        return search;
    }

    /**
     * @param n    分段位置
     * @param k    剩余分段
     * @param A    数组
     * @param memo
     */
    public double search(int n, int k, int[] A, double[][] memo) {
        if (memo[n][k] > 0) {
            return memo[n][k];
        }
        if (n < k) {
            return 0;
        }

        double cur = 0;

        for (int i = n - 1; i > 0; i--) {
            cur += A[i];
            memo[n][k] = Math.max(memo[n][k], search(i, k - 1, A, memo) + cur / (n - i));
        }

        return memo[n][k];
    }



    @Test
    public void test() {
        int[] arr = {9, 1, 2, 3, 9};

        largestSumOfAverages(arr, 3);
    }
}
