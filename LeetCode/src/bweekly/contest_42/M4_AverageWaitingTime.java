package bweekly.contest_42;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 26/12/2020 10:54 PM.
 */
public class M4_AverageWaitingTime {

    public double averageWaitingTime(int[][] customers) {
        int row = customers.length;
        int col = customers[0].length;

//        int[] startCook = new int[row];
        int startTime = customers[0][0];
        double allWaitTime = customers[0][1];
        System.out.println(allWaitTime);
        for (int i = 1; i < row; i++) {
            int preNeedTime = customers[i - 1][1];
            int preEndTime = startTime + preNeedTime;
            int curTime = customers[i][0];

            if (curTime >= preEndTime) {
                startTime = curTime;
            } else {
                startTime = preEndTime;
            }

            allWaitTime += (startTime + customers[i][1]) - curTime;
            System.out.println(allWaitTime);
        }


        return new BigDecimal(allWaitTime).divide(BigDecimal.valueOf(row), 5, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @Test
    public void test1() {
        int[][] num = {{5, 2}, {5, 4}, {10, 3}, {20, 1}};
        double result = averageWaitingTime(num);
        System.out.println(result);
        Assert.assertEquals(3.25000, result);
    }

    @Test
    public void test2() {
        int[][] num = {{1, 2}, {2, 5}, {4, 3}};

        double result = averageWaitingTime(num);
        System.out.println(result);
        Assert.assertEquals(5.00000, result);
    }

    @Test
    public void test3() {
        int[][] num = {{2, 3}, {6, 3}, {7,5},{11,3}, {15,2}, {18, 1}};

        double result = averageWaitingTime(num);
        System.out.println(result);
    }
}
