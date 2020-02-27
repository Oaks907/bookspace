package S1201_S1300;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * https://leetcode.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/
 * Create by haifei on 28/2/2020 12:37 AM.
 */
public class S1292_MaximumSideLengthofaSquarewithSumLessthanorEqualtoThreshold {

    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] sum = new int[m + 1][n + 1];

        int res = 0;
        int len = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + mat[i - 1][j - 1];
                PrintUtils.printArray(sum);
                System.out.println("-----------");
                if (i >= len && j >= len
                            && sum[i][j] - sum[i - len][j] - sum[i][j - len] + sum[i - len][j - len] <= threshold) {
                    res = len++;
                    System.out.println("res:" + res + " len:" + len);
                }
            }
        }

        return res;
    }

    @Test
    public void test() {

        int[][] arr = {{1, 1, 3, 2, 4, 3, 2}, {1, 1, 3, 2, 4, 3, 2}, {1, 1, 3, 2, 4, 3, 2}};

        int result = maxSideLength(arr, 4);

        Assert.assertEquals(2, result);
    }

    @Test
    public void test1() {

        int[][] arr = {{2, 2, 2, 2, 2}, {2, 2, 2, 2, 2}, {2, 2, 2, 2, 2}, {2, 2, 2, 2, 2}, {2, 2, 2, 2, 2}};

        int result = maxSideLength(arr, 1);

        Assert.assertEquals(0, result);
    }

    @Test
    public void test2() {

        int[][] arr = {{1, 1, 1, 1}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}};

        int result = maxSideLength(arr, 6);

        Assert.assertEquals(3, result);
    }

    @Test
    public void test3() {

        int[][] arr = {{18, 70}, {61, 1}, {25, 85}, {14, 40}, {11, 96}, {97, 96}, {63, 45}};

        int result = maxSideLength(arr, 40184);

        Assert.assertEquals(2, result);
    }
}
