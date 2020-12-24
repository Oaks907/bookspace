package c15;

import java.util.Arrays;

import org.junit.Test;

/**
 * 第二版动态规划解钢条分割。
 * 自包含很多重复运算
 * Create by haifei on 30/11/2020 8:29 PM.
 */
public class CutRod_2_topToBottom {

    int result = 0;
    int[] memoryArr;

    /**
     * 记忆化查找，自顶向下查找
     * @return
     */
    public int cut_rod_memoized(int[] price, int len) {
        memoryArr = new int[len + 1];
        Arrays.fill(memoryArr, -1);
        topToBottom(price, len, memoryArr);
        return result;
    }

    public int topToBottom(int[] price, int len, int[] mem) {
        if (mem[len] >= 0) {
            return mem[len];
        }

        if (len == 0) {
            return 0;
        }

        for (int i = 1; i < len; i++) {
            result = Math.max(result, price[i] + topToBottom(price, len - i, mem));
        }

        mem[len] = result;
        return result;
    }

    @Test
    public void test() {
        int[] price = {1, 1, 8, 9, 10, 17, 17, 20, 24, 30};
        int result = cut_rod_memoized(price, 4);
        System.out.println(result);
    }
}
