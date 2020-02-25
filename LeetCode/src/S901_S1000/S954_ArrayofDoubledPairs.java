package S901_S1000;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * https://leetcode.com/problems/array-of-doubled-pairs/
 * Create by haifei on 24/2/2020 9:24 PM.
 */
public class S954_ArrayofDoubledPairs {

    public boolean canReorderDoubled(int[] A) {

        Map<Integer, Integer> map = new HashMap<>();

        Arrays.sort(A);

        //        PrintUtils.printArray(A);

        for (int i : A) {
            int val;
            if (i >= 0) {
                if (i % 2 != 0) {
                    map.put(i, map.getOrDefault(i, 0) + 1);
                    continue;
                }
                val = i / 2;
            } else {
                val = i * 2;
            }

            if (map.containsKey(val) && map.get(val) != 0) {
                map.put(val, map.get(val) - 1);
            } else {
                map.put(i, map.getOrDefault(i, 0) + 1);
            }
        }

                System.out.println("map --> " + map);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void test9() {

        int[] arr = {2, 1, 2, 1, 1, 1, 2, 2};

        boolean result = canReorderDoubled(arr);

        Assert.assertTrue(result);
    }

    @Test
    public void test8() {

        int[] arr = {-4, -6, -1, -2, -1, -1, -3, -8};

        boolean result = canReorderDoubled(arr);

        Assert.assertFalse(result);
    }

    @Test
    public void test7() {

        int[] arr = {-2, -2, 1, -2, -1, 2};

        boolean result = canReorderDoubled(arr);

        Assert.assertFalse(result);
    }

    @Test
    public void test6() {

        int[] arr = {-8, -4, -2, -1, 0, 0, 1, 2, 4, 8};

        boolean result = canReorderDoubled(arr);

        Assert.assertTrue(result);
    }

    @Test
    public void test5() {

        int[] arr = {-3, -6};

        boolean result = canReorderDoubled(arr);

        Assert.assertTrue(result);
    }

    @Test
    public void test4() {

        int[] arr = {4, -2, 2, -4};

        boolean result = canReorderDoubled(arr);

        Assert.assertTrue(result);
    }

    @Test
    public void test1() {

        int[] arr = {3, 1, 3, 6};

        boolean result = canReorderDoubled(arr);

        Assert.assertFalse(result);
    }

    @Test
    public void test2() {

        int[] arr = {2, 1, 2, 6};

        boolean result = canReorderDoubled(arr);

        Assert.assertFalse(result);
    }

    @Test
    public void test3() {

        int[] arr = {1, 2, 4, 16, 8, 4};

        boolean result = canReorderDoubled(arr);

        Assert.assertFalse(result);
    }
}
