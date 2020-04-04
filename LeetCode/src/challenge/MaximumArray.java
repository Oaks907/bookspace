package challenge;

import org.junit.Test;

/**
 * Create by haifei on 4/4/2020 1:20 PM.
 */
public class MaximumArray {

    public int maxSubArray(int[] nums) {

        if (nums == null || 0 == nums.length) {
            return 0;
        }

        int sum = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (sum > max) {
                max = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }

        return max;
    }

    @Test
    public void test() {

        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println(maxSubArray(arr));
    }
}
