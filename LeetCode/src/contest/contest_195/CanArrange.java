package contest.contest_195;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 28/6/2020 11:14 AM.
 */
public class CanArrange {

    // TLE 时间超限
    public boolean canArrange_tle(int[] arr, int k) {
        Arrays.sort(arr);
        boolean[] used = new boolean[arr.length];

        for (int i = 0; i < arr.length; i++) {
            if (used[i]) {
                continue;
            }
            for (int j = i + 1; j < arr.length; j++) {
                if (used[j]) {
                    continue;
                }
                int sum = arr[i] + arr[j];
                if (sum % k == 0) {
                    used[i] = true;
                    used[j] = true;
                    break;
                }
            }
            if (!used[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean canArrange(int[] arr, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int val = arr[i] % k;
            if (val < 0) {
                val = k + val;
            }
            if (map.containsKey(k - val) && map.get(k - val) != 0) {
                if (map.get(k - val) == 1) {
                    map.remove(k - val);
                } else {
                    map.put(k - val, map.get(k - val) - 1);
                }
            } else {
                map.put(val, map.getOrDefault(val, 0) + 1);
            }
            if (val == 0 && map.get(val) == 2) {
                map.remove(val);
            }
        }

        return map.isEmpty();
    }

    @Test
    public void test6() {
        int[] arr = {-4, -7, 5, 2, 9, 1, 10, 4, -8, -3};

        boolean result = canArrange(arr, 3);

        Assert.assertTrue(result);
    }

    @Test
    public void test1() {
        int[] arr = {1, 2, 3, 4, 5, 10, 6, 7, 8, 9};

        boolean result = canArrange(arr, 5);

        Assert.assertTrue(result);
    }

    @Test
    public void test2() {
        int[] arr = {1, 2, 3, 4, 5, 6};

        boolean result = canArrange(arr, 7);

        Assert.assertTrue(result);
    }

    @Test
    public void test3() {
        int[] arr = {1, 2, 3, 4, 5, 6};

        boolean result = canArrange(arr, 10);

        Assert.assertFalse(result);
    }

    @Test
    public void test4() {
        int[] arr = {-10, 10};

        boolean result = canArrange(arr, 2);

        Assert.assertTrue(result);
    }

    @Test
    public void test5() {
        int[] arr = {-1, 1, -2, 2, -3, 3, -4, 4};

        boolean result = canArrange(arr, 3);

        Assert.assertTrue(result);
    }
}
