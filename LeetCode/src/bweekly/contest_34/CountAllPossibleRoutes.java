package bweekly.contest_34;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 5/9/2020 11:34 PM.
 */
public class CountAllPossibleRoutes {

    // dp[i][j] = k , 表示从i的位置, 还剩下 j的油 , 到达终点的路的数量
    int[][] dp;
    // 记录油耗量
    int[][] map;
    int MOD = 1000000000 + 7;

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int len = locations.length;
        map = new int[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                map[i][j] = Math.abs(locations[i] - locations[j]);
            }
        }

        dp = new int[len][fuel + 1];

        for (int[] temp : dp) {
            Arrays.fill(temp, -1);
        }

        return recursion(start, finish, fuel);
    }

    public int recursion(int start, int finish, int fuel) {
        if (dp[start][fuel] == -1) {
            int temp = start == finish ? 1 : 0;
            for (int i = 0; i < map.length; i++) {
                if (i != start && map[start][i] <= fuel) {
                    temp += recursion(i, finish, fuel - map[start][i]);
                    temp %= MOD;
                }
            }
            dp[start][fuel] = temp;
        }
        return dp[start][fuel];
    }

    private static final int M = (int) 1e9 + 7;

    public int countRoutes_dfs(int[] locations, int start, int finish, int fuel) {
        int[][] dp = new int[locations.length][fuel + 1];
        for (int[] temp : dp) {
            Arrays.fill(temp, -1);
        }
        return dfs(locations, start, finish, fuel, dp) % M;
    }

    private int dfs(int[] locations, int start, int finish, int fuel, int[][] memo) {
        if (fuel == 0 && start == finish) {
            return 1;
        }
        if (fuel <= 0) {
            return 0;
        }
        if (memo[start][fuel] != -1) {
            return memo[start][fuel];
        }

        int count = start == finish ? 1 : 0;
        for (int i = 0; i < locations.length; i++) {
            if (i != start) {
                count += dfs(locations, i, finish, fuel - Math.abs(locations[i] - locations[start]), memo);
            }
        }
        count %= MOD;
        memo[start][fuel] = count;
        return count;
    }

    @Test
    public void test() {
        int[] arr = {2, 3, 6, 8, 4};

        int result = countRoutes(arr, 1, 3, 5);


        Assert.assertEquals(4, result);

        result = countRoutes_dfs(arr, 1, 3, 5);

        Assert.assertEquals(4, result);
    }

    @Test
    public void test1() {
        int[] arr = {4, 3, 1};

        int result = countRoutes(arr, 1, 0, 6);

        Assert.assertEquals(5, result);

        result = countRoutes_dfs(arr, 1, 0, 6);

        Assert.assertEquals(5, result);
    }
}
