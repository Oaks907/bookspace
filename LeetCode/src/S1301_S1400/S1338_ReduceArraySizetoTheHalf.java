package S1301_S1400;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/reduce-array-size-to-the-half/
 * Create by haifei on 1/3/2020 12:12 AM.
 */
public class S1338_ReduceArraySizetoTheHalf {

    public int minSetSize(int[] arr) {

        Map<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer>[] list = new ArrayList[arr.length + 1];

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        for (Integer item : map.keySet()) {
            int count = map.get(item);
            if (list[count] == null) {
                list[count] = new ArrayList<>();
            }
            list[count].add(item);
        }

        int steps = 0, res = 0;
        for (int i = arr.length; i > 0; i--) {
            ArrayList<Integer> cur = list[i];
            if (cur == null) {
                continue;
            }
            for (Integer num : cur) {
                steps += i;
                res++;
                if (steps >= arr.length / 2) {
                    return res;
                }
            }
        }

        return res;
    }

    @Test
    public void test() {
        int[] arr = {3, 3, 3, 3, 5, 5, 5, 2, 2, 7};

        int result = minSetSize(arr);

        Assert.assertEquals(2, result);
    }

    @Test
    public void test1() {
        int[] arr = {7, 7, 7, 7, 7, 7};

        int result = minSetSize(arr);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test2() {
        int[] arr = {1, 9};

        int result = minSetSize(arr);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test3() {
        int[] arr = {1000, 1000, 3, 7};

        int result = minSetSize(arr);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test4() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int result = minSetSize(arr);

        Assert.assertEquals(5, result);
    }
}
