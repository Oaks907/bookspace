package c15;

import org.junit.Test;

import utils.PrintUtils;

/**
 * 第二版动态规划解钢条分割。
 * 采用自底向上的方法进行处理
 * Create by haifei on 30/11/2020 8:29 PM.
 */
public class CutRod_2_bottomToTop_v2 {

    public int cut_rod(int[] price, int len) {

        int[] r = new int[len];
        int[] s = new int[len];
        int result = 0;

        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= i; j++) {
                if (result < price[j] + r[i - j]) {
                    result = price[j] + r[i - j];
                    s[i] = j;
                }
            }
            r[i] = result;
        }

        PrintUtils.printArray(s);
        return r[len - 1];
    }

    @Test
    public void test() {
        int[] price = {1, 1, 8, 9, 10, 17, 17, 20, 24, 30};
        int result = cut_rod(price, 4);
        System.out.println(result);
    }
}
