package S401_500;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 19/3/2020 12:22 AM.
 */
public class S494_TargetSum {

    /**
     * TLE
     */
    List<List<Integer>> result = new ArrayList<>();

    public int findTargetSumWays_TLE(int[] nums, int S) {
        helper(nums, 0, new ArrayList<>(), 0, S);
        return result.size();
    }

    public void helper(int[] nums, int index, List<Integer> list, int curSum, int S) {
        if (index == nums.length) {
            if (curSum == S) {
                result.add(list);
            }
            return;
        }

        ArrayList<Integer> list1 = new ArrayList<>(list);
        list1.add(-nums[index]);
        helper(nums, index + 1, list1, curSum - nums[index], S);

        ArrayList<Integer> list2 = new ArrayList<>(list);
        list2.add(nums[index]);
        helper(nums, index + 1, list2, curSum + nums[index], S);
    }

    /**
     * slow
     */
    public int findTargetSumWays_SLOW(int[] nums, int S) {
        helper(nums, 0, 0, S);
        return count;
    }

    int count = 0;

    public void helper(int[] nums, int index, int curSum, int S) {
        if (index == nums.length) {
            if (curSum == S) {
                count++;
            }
            return;
        }
        helper(nums, index + 1, curSum - nums[index], S);
        helper(nums, index + 1, curSum + nums[index], S);
    }

    public int findTargetSumWays(int[] nums, int S) {
        helper(nums, 0, 0, S);
        return count;
    }

    @Test
    public void test() {

        int[] nums = {1, 1, 1, 1, 1};

        int result = findTargetSumWays(nums, 3);

        Assert.assertEquals(5, result);
    }
}
