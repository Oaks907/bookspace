package challenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 8/4/2020 12:11 AM.
 */
public class CountingElements {

    public int countElements(int[] arr) {

        Set<Integer> set = new HashSet();
        Arrays.sort(arr);
        int sum = 0;
        for (int i = arr.length - 1; i >= 0; i--) {

            set.add(arr[i]);
            if (set.contains(arr[i] + 1)) {
                sum++;
            }
        }

        return sum;
    }

    @Test
    public void test() {

        int[] arr = {1, 2, 3};

        int result = countElements(arr);

        Assert.assertEquals(2, result);
    }

    @Test
    public void test1() {

        int[] arr = {1, 1, 3, 3, 5, 5, 7, 7};

        int result = countElements(arr);

        Assert.assertEquals(0, result);
    }

    @Test
    public void test2() {

        int[] arr = {1, 3, 2, 3, 5, 0};

        int result = countElements(arr);

        Assert.assertEquals(3, result);
    }

    @Test
    public void test3() {

        int[] arr = {1, 3, 2, 3, 5, 0};

        int result = countElements(arr);

        Assert.assertEquals(3, result);
    }
}
