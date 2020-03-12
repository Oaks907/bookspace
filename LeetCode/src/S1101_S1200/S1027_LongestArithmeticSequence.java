package S1101_S1200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/longest-arithmetic-sequence/
 * Create by haifei on 13/3/2020 1:07 AM.
 */
public class S1027_LongestArithmeticSequence {

    int res = 2;

    /**
     * Time Limit Exceeded
     *
     * @param A
     * @return
     */
    public int longestArithSeqLength_self(int[] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(A[i]);
                list.add(A[j]);
                helper(A, j, list, A[j] - A[i]);
            }
        }
        return res;
    }

    private void helper(int[] A, int index, List<Integer> list, int chaVal) {

        if (list.size() > res) {
            //            System.out.println(list);
            res = list.size();
        }

        if (index >= A.length) {
            return;
        }

        for (int i = index + 1; i < A.length; i++) {
            if (chaVal != A[i] - A[index]) {
                System.out.println(list);
                continue;
            }
            list.add(A[i]);
            helper(A, i, list, chaVal);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 使用 Map
     *
     * @param A
     * @return
     */
    public int longestArithSeqLength(int[] A) {

        Map<Integer, Integer>[] dp = new HashMap[A.length];
        int res = 2;

        for (int i = 0; i < A.length; i++) {
            dp[i] = new HashMap<>();

            for (int j = 0; j < i; j++) {
                int d = A[i] - A[j];
                dp[i].put(d, dp[j].getOrDefault(d, 1) + 1);
                res = Math.max(res, dp[i].get(d));
            }
        }

        return res;
    }

    @Test
    public void test() {

        int[] arr = {3, 6, 9, 12};

        int res = longestArithSeqLength(arr);

        Assert.assertEquals(4, res);
    }

    @Test
    public void test1() {

        int[] arr = {9, 4, 7, 2, 10};

        int res = longestArithSeqLength(arr);

        Assert.assertEquals(3, res);
    }

    @Test
    public void test2() {

        int[] arr = {20, 1, 15, 3, 10, 5, 8};

        int res = longestArithSeqLength(arr);

        Assert.assertEquals(4, res);
    }
}
