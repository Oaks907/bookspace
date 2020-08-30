package contest.contest_204;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 30/8/2020 10:34 AM.
 */
public class DetectPatternofLengthMRepeatedKorMoreTimes {

    public boolean containsPattern(int[] arr, int m, int k) {

        int len = arr.length;

        for (int i = 0; i < len - m; i++) {

            int count = 1;

            for (int j = i + m; j <= len - m; j = j + m) {

                boolean allEquals = true;
                for (int l = 0; l < m; l++) {
                    int originIndex = l + i;
                    int curIndex = l + j;
                    if (arr[originIndex] != arr[curIndex]) {
                        allEquals = false;
                        break;
                    }
                }
                if (allEquals) {
                    count++;
                    if (count >= k) {
                        return true;
                    }
                } else {
                    break;
                }
            }
        }

        return false;
    }

    @Test
    public void test1() {
        int[] arr = {1, 2, 4, 4, 4, 4};

        boolean result = containsPattern(arr, 1, 3);

        Assert.assertEquals(true, result);
    }

    @Test
    public void test2() {
        int[] arr = {1, 2, 1, 2, 1, 1, 1, 3};

        boolean result = containsPattern(arr, 2, 2);

        Assert.assertEquals(true, result);
    }

    @Test
    public void test3() {
        int[] arr = {1, 2, 1, 2, 1, 3};

        boolean result = containsPattern(arr, 2, 3);

        Assert.assertEquals(false, result);
    }

    @Test
    public void test4() {
        int[] arr = {1, 2, 3, 1, 2};

        boolean result = containsPattern(arr, 2, 2);

        Assert.assertEquals(false, result);
    }

    @Test
    public void test5() {
        int[] arr = {2, 2, 2, 2};

        boolean result = containsPattern(arr, 2, 3);

        Assert.assertEquals(false, result);
    }
}
