package contest.contest_206;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 13/9/2020 12:19 PM.
 */
public class MinCosttoConnectAllPoints {

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int inf = (int) 1e9;
        int[] dist = new int[n];
        Arrays.fill(dist, inf);

        dist[0] = 0;
        boolean[] added = new boolean[n];

        for (int i = 0; i < n; i++) {

            int head = -1;

            for (int j = 0; j < n; j++) {
                if (added[j]) {
                    continue;
                }

                if (head == -1 || dist[j] < dist[head]) {
                    head = j;
                }
            }

            added[head] = true;

            for (int j = 0; j < n; j++) {
                if (added[j]) {
                    continue;
                }
                int cand = dist(points[j], points[head]);

                if (dist[j] > cand) {
                    dist[j] = cand;
                }
            }
        }

        return Arrays.stream(dist).sum();
    }

    public int dist(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    @Test
    public void test6() {
        int[][] points = {{2, -3}, {-17, -8}, {13, 8}, {-17, -15}};

        int result = minCostConnectPoints(points);

        Assert.assertEquals(53, result);
    }

    @Test
    public void test() {
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};

        int result = minCostConnectPoints(points);

        Assert.assertEquals(20, result);
    }

    @Test
    public void test1() {
        int[][] points = {{3, 12}, {-2, 5}, {-4, 1}};

        int result = minCostConnectPoints(points);

        Assert.assertEquals(18, result);
    }

    @Test
    public void test2() {
        int[][] points = {{0, 0}, {1, 1}, {1, 0}, {-1, 1}};

        int result = minCostConnectPoints(points);

        Assert.assertEquals(4, result);
    }

    @Test
    public void test3() {
        int[][] points = {{-1000000, -1000000}, {1000000, 1000000}};

        int result = minCostConnectPoints(points);

        Assert.assertEquals(4000000, result);
    }

    @Test
    public void test4() {
        int[][] points = {{0, 0}};

        int result = minCostConnectPoints(points);

        Assert.assertEquals(0, result);
    }
}
