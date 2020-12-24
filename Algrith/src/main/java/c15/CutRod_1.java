package c15;

import org.junit.Test;

/**
 * 第一版动态规划解钢条分割。
 * 包含很多重复运算
 * Create by haifei on 30/11/2020 8:29 PM.
 */
public class CutRod_1 {

    int result = Integer.MIN_VALUE;

    public int cut_rod(int[] price, int len) {
        if (len == 0) {
            return 0;
        }
        result = 0;
        for (int i = 1; i < len; i++) {
            result = Math.max(result, price[i] + cut_rod(price, len - i));
        }

        return result;
    }

    @Test
    public void test() {
        int[] price = {1, 1, 8, 9, 10, 17, 17, 20, 24, 30};
        int result = cut_rod(price, 4);
        System.out.println(result);
    }
}
