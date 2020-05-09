package S501_600;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 8/5/2020 9:15 PM.
 */
public class S576_OutofBoundaryPaths {

    /**
     * @param m 行
     * @param n 列
     * @param N 步数
     * @param i 起始位置
     * @param j 起始位置
     * @return
     */
    public int findPaths(int m, int n, int N, int i, int j) {
        if (N <= 0) {
            return 0;
        }

        int[][] count = new int[m][n];
        count[i][j] = 1;
        int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        int result = 0;
        int MOD = 1000000007;

        for (int step = 0; step < N; step++) {
            int[][] temp = new int[m][n];
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    for (int[] dir : dirs) {
                        int x = row + dir[0];
                        int y = col + dir[1];
                        if (x < 0 || x >= m || y < 0 || y >= n) {
                            result = (result + count[row][col]) % MOD;
                        } else {
                            temp[x][y] = (temp[x][y] + count[row][col]) % MOD;
                        }
                    }
                }
            }
            PrintUtils.printArray(temp);
            System.out.println("result = " + result);
            count = temp;
        }

        return result;
    }

    @Test
    public void test2() {
        int result = findPaths(3, 3, 2, 1, 1);

        Assert.assertEquals(4, result);
    }

    @Test
    public void test() {
        int result = findPaths(2, 2, 2, 0, 0);

        Assert.assertEquals(6, result);
    }

    @Test
    public void test1() {
        int result = findPaths(1, 3, 3, 0, 1);

        Assert.assertEquals(12, result);
    }
}
