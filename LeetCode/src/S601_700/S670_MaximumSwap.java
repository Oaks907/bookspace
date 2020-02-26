package S601_700;

import java.awt.KeyboardFocusManager;
import java.awt.font.NumericShaper;

import org.junit.Test;

import utils.PrintUtils;

/**
 * https://leetcode.com/problems/maximum-swap/
 * Create by haifei on 26/2/2020 9:55 PM.
 */
public class S670_MaximumSwap {

    public int maximumSwap(int num) {

        int[] buckets = new int[10];
        char[] chars = String.valueOf(num).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            buckets[chars[i] - '0'] = i;
        }

        PrintUtils.printArray(buckets);

        for (int i = 0; i < chars.length; i++) {

            for (int j = 9; j > chars[i] - '0'; j--) {
                if (buckets[j] > i) {
                    char tmp = chars[i];
                    chars[i] = chars[buckets[j]];
                    chars[buckets[j]] = tmp;
                    return Integer.parseInt(new String(chars));
                }
            }
        }

        return num;
    }

    @Test
    public void test() {
        int result = maximumSwap(2736);

        System.out.println(result);
    }

    @Test
    public void test2() {
        int result = maximumSwap(9973);

        System.out.println(result);
    }
}
