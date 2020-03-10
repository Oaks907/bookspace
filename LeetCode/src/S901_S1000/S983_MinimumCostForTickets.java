package S901_S1000;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 9/3/2020 10:31 PM.
 */
public class S983_MinimumCostForTickets {

    int result = Integer.MAX_VALUE;

    /**
     * 时间超出限制
     */
    public int mincostTickets_TLE(int[] days, int[] costs) {

        int[] coverDay = {1, 7, 30};

        help(days, costs, coverDay, 0, 0, 0, new ArrayList<>());

        return result;
    }

    private void help(int[] days, int[] costs, int[] coverDay, int curDayIndex, int costSum, int coverDate,
                      List<Integer> list) {

        if (costSum > result) {
            return;
        }

        if (curDayIndex >= days.length) {
            result = Math.min(result, costSum);
            //            System.out.println(list + " cost=" + costSum);
            return;
        }

        int curDay = days[curDayIndex];

        for (int i = 0; i < costs.length; i++) {
            ArrayList<Integer> arrayList = new ArrayList<>(list);
            if (coverDate < curDay) {
                arrayList.add(costs[i]);
                help(days, costs, coverDay, curDayIndex + 1, costSum + costs[i], curDay + coverDay[i] - 1, arrayList);
            } else {
                arrayList.add(0);
                help(days, costs, coverDay, curDayIndex + 1, costSum, coverDate, arrayList);
                break;
            }
        }

    }

    public int mincostTickets(int[] days, int[] costs) {

        boolean[] dayInclude = new boolean[366];

        for (int i = 0; i < days.length; i++) {
            dayInclude[days[i]] = true;
        }

        int[] minCost = new int[366];
        minCost[0] = 0;
        for (int day = 1; day < dayInclude.length; day++) {
            if (!dayInclude[day]) {
                minCost[day] = minCost[day - 1];
                continue;
            }
            int min = minCost[day - 1] + costs[0];
            min = Math.min(min, minCost[Math.max(0, day - 7)] + costs[1]);
            min = Math.min(min, minCost[Math.max(0, day - 30)] + costs[2]);
            minCost[day] = min;
        }

        return minCost[365];
    }

    @Test
    public void test() {
        int[] days = {1, 4, 6, 7, 8, 20};
        int[] costs = {2, 7, 15};

        int result = mincostTickets(days, costs);

        Assert.assertEquals(11, result);
    }

    @Test
    public void test1() {
        int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31};
        int[] costs = {2, 7, 15};

        int result = mincostTickets(days, costs);

        Assert.assertEquals(17, result);
    }
}
