package contest.contest_221;

import java.util.Comparator;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 27/12/2020 10:42 AM.
 */
public class M4_MaximumNumberofEatenApples {

    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
        int res = 0;
        for (int i = 0; ; i++) {
            if (i < apples.length && apples[i] > 0) {
                q.offer(new int[] {i + days[i], apples[i]});
            } else if (i >= apples.length && q.isEmpty()) {
                break;
            }

            while (!q.isEmpty() && (q.peek()[0] <= i || q.peek()[1] <= 0)) {
                q.poll();
            }

            if (!q.isEmpty()) {
                q.peek()[1] -= 1;
                res++;
            }
        }
        return res;
    }

    @Test
    public void test() {
        int[] apples = {1, 2, 3, 5, 2};
        int[] days = {3, 2, 1, 4, 2};

        int result = eatenApples(apples, days);
        Assert.assertEquals(7, result);

    }

    @Test
    public void test2() {
        int[] apples =
                {0, 19, 19, 19, 11, 14, 33, 0, 28, 7, 0, 28, 7, 0, 21, 16, 0, 22, 0, 13, 8, 0, 19, 0, 0, 2, 26, 2, 22,
                        0, 8, 0, 0, 27, 19, 16, 24, 0, 20, 26, 20, 7, 0, 0, 29, 0, 0, 16, 19, 0, 0, 0, 29, 30, 17, 0,
                        23, 0, 0, 26, 24, 13, 3, 0, 21, 0, 18, 0};
        int[] days =
                {0, 5, 1, 16, 7, 10, 54, 0, 40, 2, 0, 23, 4, 0, 20, 18, 0, 40, 0, 22, 8, 0, 35, 0, 0, 3, 24, 1, 8, 0,
                        10, 0, 0, 2, 38, 8, 4, 0, 36, 33, 14, 9, 0, 0, 56, 0, 0, 21, 27, 0, 0, 0, 14, 20, 18, 0, 42, 0,
                        0, 44, 3, 8, 3, 0, 10, 0, 27, 0};

        int result = eatenApples(apples, days);
        Assert.assertEquals(102, result);
    }

    @Test
    public void test1() {
        int[] apples = {3, 0, 0, 0, 0, 2};
        int[] days = {3, 0, 0, 0, 0, 2};

        int result = eatenApples(apples, days);
        Assert.assertEquals(5, result);
    }

}
