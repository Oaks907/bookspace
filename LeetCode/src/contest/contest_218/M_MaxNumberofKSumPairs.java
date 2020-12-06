package contest.contest_218;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 6/12/2020 10:57 AM.
 */
public class M_MaxNumberofKSumPairs {

    public int maxOperations(int[] nums, int k) {

        int len = nums.length;
        int left = 0;
        int right = len - 1;
        int result = 0;
        Arrays.sort(nums);
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == k) {
                left++;
                right--;
                result++;
            } else if (sum < k) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }

    @Test
    public void test_1() {
        int[] nums = {1, 2, 3, 4};

        int result = maxOperations(nums, 5);

        Assert.assertEquals(2, result);
    }

    @Test
    public void test_2() {
        int[] nums = {3, 1, 3, 4, 3};

        int result = maxOperations(nums, 6);

        Assert.assertEquals(1, result);
    }
}
