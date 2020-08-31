package S1101_S1200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.sun.org.apache.xpath.internal.operations.Mod;

/**
 * https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
 * Create by haifei on 14/3/2020 3:18 PM.
 */
public class S1155_NumberofDiceRollsWithTargetSum {

    int res = 0;

    /**
     * TLE 版本
     */
    public int numRollsToTarget_TLE(int d, int f, int target) {
        ArrayList<Integer> list = new ArrayList<>();
        helper(d, f, 0, list, 0, target);

        return res;
    }

    private void helper(int d, int f, int curD, List<Integer> list, int sum, int target) {

        if (target == sum && d == curD) {
            res++;
            return;
        } else if (target < sum) {
            return;
        }

        if (curD >= d) {
            return;
        }

        for (int i = 1; i <= f; i++) {
            sum += i;
            if (sum > target) {
                break;
            }
            list.add(i);
            helper(d, f, curD + 1, list, sum, target);
            list.remove(list.size() - 1);
            sum -= i;
        }
    }

    int MOD = 1000000000 + 7;
    Map<String, Integer> memo = new HashMap<>();

    public int numRollsToTarget(int d, int f, int target) {
        if (d == 0 && target == 0) {
            return 1;
        }
        if (d == 0 || target == 0) {
            return 0;
        }
        String str = d + " " + target;
        if (memo.containsKey(str)) {
            return memo.get(str);
        }
        int res = 0;
        for (int i = 1; i <= f; i++) {
            if (target >= i) {
                res = (res + numRollsToTarget(d - 1, f, target - i)) % MOD;
            } else {
                break;
            }
        }
        memo.put(str, res);

        return res;
    }

    /**
     * DP
     */
    public int numRollsToTarget_DP(int d, int f, int target) {
        int MOD = 1000000007;
        int[][] dp = new int[d + 1][target + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= d; i++) {
            for (int j = 1; j <= target; j++) {
                if (j > i * f) {
                    break;
                } else {
                    for (int k = 1; k <= j && k <= f; k++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % MOD;
                    }
                }
            }
        }

        return dp[d][target];
    }

    @Test
    public void test() {

        int result = numRollsToTarget(1, 6, 3);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test2() {

        int result = numRollsToTarget(2, 6, 7);

        Assert.assertEquals(6, result);
    }

    @Test
    public void test3() {

        int result = numRollsToTarget(2, 5, 10);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test4() {

        int result = numRollsToTarget(1, 2, 3);

        Assert.assertEquals(0, result);
    }

    @Test
    public void test5() {

        int result = numRollsToTarget(30, 30, 500);

        Assert.assertEquals(222616187, result);
    }
}
