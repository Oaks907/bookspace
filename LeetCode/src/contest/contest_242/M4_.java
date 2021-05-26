package contest.contest_242;

import org.junit.Test;

/**
 * Create by haifei on 23/5/2021 11:10 AM.
 */
public class M4_ {

    public int minSpeedOnTime(int[] dist, double hour) {

        int len = dist.length;

        if (Math.ceil(hour) < len) {
            return -1;
        }

        int left = 1, right = Integer.MAX_VALUE;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (checkMid(dist, mid, hour)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public boolean checkMid(int[] dist, int speed, double hour) {

        double cnt = 0.0;

        for (int i = 0; i < dist.length - 1; i++) {
            cnt += (dist[i] + speed - 1) / speed;
        }

        cnt += (double) (dist[dist.length - 1]) / speed;

        return hour >= cnt;
    }

    @Test
    public void test2() {
        int[] arr = {5, 3, 4, 6, 2, 2, 7};
        System.out.println(minSpeedOnTime(arr, 10.92));
    }

    @Test
    public void test1() {

        int[] arr = {1, 1, 10000};
        System.out.println(minSpeedOnTime(arr, 2.01));
    }

    @Test
    public void test() {

        int[] arr = {1, 3, 2};
        System.out.println(minSpeedOnTime(arr, 2.7));
    }
}
