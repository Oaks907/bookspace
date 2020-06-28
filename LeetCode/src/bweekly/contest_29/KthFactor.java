package bweekly.contest_29;

import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 27/6/2020 10:36 PM.
 */
public class KthFactor {

    public int kthFactor(int n, int k) {

        int[] factor = new int[k];
        int index = 0;

        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                factor[index++] = i;
            }
            if (index >= k) {
                break;
            }
        }

//        PrintUtils.printArray(factor);

        return factor[k - 1] == 0 ? -1 : factor[k - 1];
    }

    @Test
    public void test() {
        System.out.println(kthFactor(12, 3));
    }

    @Test
    public void test1() {
        System.out.println(kthFactor(7, 2));
    }
}
