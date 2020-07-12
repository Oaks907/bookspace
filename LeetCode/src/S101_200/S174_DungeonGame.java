package S101_200;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 12/7/2020 10:19 PM.
 */
public class S174_DungeonGame {

    int[][] memo;

    public int calculateMinimumHP(int[][] dungeon) {
        memo = new int[dungeon.length][dungeon[0].length];
        return dfs(dungeon, dungeon.length, dungeon[0].length, 0, 0);
    }

    private int dfs(int[][] dungeon, int m, int n, int i, int j) {
        if (i == m - 1 && j == n - 1) {
            return Math.max(1 - dungeon[i][j], 1);
        }

        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int minRes = 0;
        if (i == m - 1) {
            minRes = Math.max(dfs(dungeon, m, n, i, j + 1) - dungeon[i][j], 1);
        } else if (j == n - 1) {
            minRes = Math.max(dfs(dungeon, m, n, i + 1, j) - dungeon[i][j], 1);
        } else {
            minRes = Math.max(Math.min(dfs(dungeon, m, n, i + 1, j), dfs(dungeon, m, n, i, j + 1)), 1);
        }

        return memo[i][j] = minRes;
    }

    @Test
    public void test4() {
        int[][] arr = {{1, -3, 3}, {0, -2, 0}, {-3, -3, -3}};
        int result = calculateMinimumHP(arr);

        Assert.assertEquals(3, result);
    }

    @Test
    public void test3() {
        int[][] arr = {{0, -5}, {0, 0}};
        int result = calculateMinimumHP(arr);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test2() {
        int[][] arr = {{1, -2, 3}, {2, -2, -2}};
        int result = calculateMinimumHP(arr);

        Assert.assertEquals(2, result);
    }

    @Test
    public void test1() {
        int[][] arr = {{0, 0}};
        int result = calculateMinimumHP(arr);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test() {
        int[][] arr = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        int result = calculateMinimumHP(arr);

        Assert.assertEquals(7, result);
    }
}
