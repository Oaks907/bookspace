package contest.contest_192;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 7/6/2020 10:31 AM.
 */
public class ShuffletheArray {

    public int[] shuffle(int[] nums, int n) {

        List<Integer> list = new ArrayList<>();
        int start = 0;
        while (start < n) {
            int xVal = nums[start];
            int yVal = nums[n + start];
            list.add(xVal);
            list.add(yVal);
            start++;
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }

    @Test
    public void test() {
        int[] res = {2, 5, 1, 3, 4, 7};

        int[] result = shuffle(res, 3);

        PrintUtils.printArray(result);
    }

}
