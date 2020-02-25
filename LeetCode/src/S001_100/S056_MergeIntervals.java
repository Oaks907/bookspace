package S001_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import utils.PrintUtils;

/**
 * https://leetcode.com/problems/merge-intervals/
 * Create by haifei on 25/2/2020 11:10 AM.
 */
public class S056_MergeIntervals {

    public int[][] merge(int[][] intervals) {

        if (intervals.length <= 1) {
            return intervals;
        }

        // sort
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);
        for (int[] interval : intervals) {

            if (interval[0] <= newInterval[1]) {
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            } else {
                newInterval = interval;
                result.add(newInterval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    @Test
    public void test() {
        int[][] arr = {{2, 6}, {8, 10}, {1, 3}, {15, 18}};

        int[][] result = merge(arr);

        PrintUtils.printArray(result);
    }

    @Test
    public void test1() {
        int[][] arr = {{1, 4}, {4, 5}};

        int[][] result = merge(arr);

        PrintUtils.printArray(result);
    }
}
