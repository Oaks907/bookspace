package contest.contest_205;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 6/9/2020 11:14 AM.
 */
public class MinimumDeletionCosttoAvoidRepeatingLetters {

    public int minCost(String s, int[] cost) {
        int len = s.length();
        int[][] dp = new int[len + 1][2];

        int ans = 0;
        for (int i = 0; i < len - 1; i++) {
            char cur = s.charAt(i);
            int nextIndex = i + 1;
            int localCost = 0;
            int maxCost = cost[i];
            while (nextIndex < len && cur == s.charAt(nextIndex)) {
                if (maxCost > cost[nextIndex]) {
                    localCost += cost[nextIndex];
                } else {
                    localCost += maxCost;
                    maxCost = cost[nextIndex];
                }
                nextIndex++;
            }
            ans += localCost;
            i = nextIndex - 1;
        }

        return ans;
    }

    @Test
    public void test() {
        int[] arr = {1, 2, 3, 4, 5};
        int result = minCost("abaac", arr);

        Assert.assertEquals(3, result);
    }

    @Test
    public void test1() {
        int[] arr = {1, 2, 3};
        int result = minCost("abc", arr);

        Assert.assertEquals(0, result);
    }

    @Test
    public void test2() {
        int[] arr = {1, 2, 3, 4, 1};
        int result = minCost("aabaa", arr);

        Assert.assertEquals(2, result);
    }

    @Test
    public void test3() {
        int[] arr = {4, 9, 3, 8, 8, 9};
        int result = minCost("bbbaaa", arr);

        Assert.assertEquals(23, result);
    }
}
