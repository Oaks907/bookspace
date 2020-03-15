package contest.contest_180;

import java.util.Arrays;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 15/3/2020 11:49 AM.
 */
public class MaximumPerformanceofaTeam {

    // TLE
    public int maxPerformance_TLE(int n, int[] speed, int[] efficiency, int k) {

        maxPerformanceHelper(n, speed, efficiency, k, 0, 0, Integer.MAX_VALUE);

        return maxPerformance;
    }

    int maxPerformance = 0;

    public void maxPerformanceHelper(int n, int[] speed, int[] efficiency, int k, int start, int sumSpeed,
                                     int minEfficiency) {

        int performance = sumSpeed * minEfficiency;
        //        System.out.println(sumSpeed + " " + minEfficiency + " " + performance);
        maxPerformance = Math.max(performance, maxPerformance);

        if (k <= 0 || start > n) {
            return;
        }

        for (int i = start; i < speed.length; i++) {
            maxPerformanceHelper(n, speed, efficiency, k - 1, i + 1, sumSpeed + speed[i],
                    Math.min(minEfficiency, efficiency[i]));
        }
    }

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {

        int MOD = (int) (1e9 + 7);
        int[][] engineers = new int[n][2];
        for (int i = 0; i < n; ++i) {
            engineers[i] = new int[] {efficiency[i], speed[i]};
        }

        Arrays.sort(engineers, (a, b) -> b[0] - a[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (a, b) -> a - b);
        long res = Long.MIN_VALUE, totalSpeed = 0;

        for (int[] engineer : engineers) {
            if (pq.size() == k) {
                totalSpeed -= pq.poll();
            }
            pq.add(engineer[1]);

            totalSpeed = totalSpeed + engineer[1];
            res = Math.max(res, (totalSpeed * engineer[0]));
        }

        return (int) (res % MOD);
    }

    @Test
    public void test3() {
        int[] speed = {2, 8, 2};
        int[] efficiency = {2, 7, 1};

        int result = maxPerformance(3, speed, efficiency, 2);

        Assert.assertEquals(56, result);
    }

    @Test
    public void test() {
        int[] speed = {2, 10, 3, 1, 5, 8};
        int[] efficiency = {5, 4, 3, 9, 7, 2};

        int result = maxPerformance(6, speed, efficiency, 2);

        Assert.assertEquals(60, result);
    }

    @Test
    public void test1() {
        int[] speed = {2, 10, 3, 1, 5, 8};
        int[] efficiency = {5, 4, 3, 9, 7, 2};

        int result = maxPerformance(6, speed, efficiency, 3);

        Assert.assertEquals(68, result);
    }

    @Test
    public void test2() {
        int[] speed = {2, 10, 3, 1, 5, 8};
        int[] efficiency = {5, 4, 3, 9, 7, 2};

        int result = maxPerformance(6, speed, efficiency, 4);

        Assert.assertEquals(72, result);
    }

}
