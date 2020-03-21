package contest.contest_34;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 21/3/2020 7:55 PM.
 */
public class ArrayNesting {

    private List<Integer> list = new ArrayList<>();
    int result = 0;
    private List<Integer> hasFoundList = new ArrayList<>();

    public int arrayNesting_TLE(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            if (hasFoundList.contains(i)) {
                continue;
            }
            hasFoundList.add(i);
            helper(nums, i);
        }

        return result;
    }

    public void helper(int[] nums, int cur) {

        int val = nums[cur];

        if (list.contains(val)) {
            result = Math.max(result, list.size());
            return;
        }

        hasFoundList.add(val);

        list.add(val);
        helper(nums, val);
        list.remove(list.size() - 1);
    }

    public int arrayNesting(int[] nums) {

        int result = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            result = Math.max(result, reverse(nums, i));
        }

        return result;
    }

    public int reverse(int[] nums, int i) {
        int count = 0;
        int value = i;

        while (nums[value] != -1) {
            count++;
            int temp = nums[value];
            nums[value] = -1;
            value = temp;
        }

        return count;
    }

    @Test
    public void test() {
        int[] arr = {5, 4, 0, 3, 1, 6, 2};

        int result = arrayNesting(arr);

        Assert.assertEquals(4, result);
    }
}
