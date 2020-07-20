package contest.contest_198;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 19/7/2020 10:31 AM.
 */
public class WaterBottles {

    public int numWaterBottles(int numBottles, int numExchange) {
        int result = 0;
        int addition = 0;
        while (numBottles > 0) {
            result += numBottles;
            // 空瓶总数
            int all = (numBottles + addition);
            // 能换的瓶数
            numBottles = all / numExchange;
            addition = all % numExchange;
        }

        return result;
    }

    @Test
    public void test() {
        int result = numWaterBottles(9, 3);

        Assert.assertEquals(13, result);
    }

    @Test
    public void test2() {
        int result = numWaterBottles(15, 4);

        Assert.assertEquals(19, result);
    }

    @Test
    public void test3() {
        int result = numWaterBottles(5, 5);

        Assert.assertEquals(6, result);
    }

    @Test
    public void test4() {
        int result = numWaterBottles(2, 3);

        Assert.assertEquals(2, result);
    }

}
