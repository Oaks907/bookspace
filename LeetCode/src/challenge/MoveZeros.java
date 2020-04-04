package challenge;

import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 4/4/2020 7:41 PM.
 */
public class MoveZeros {

    public void moveZeroes(int[] nums) {

        int zeroSize = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroSize++;
            } else {
                nums[i - zeroSize] = nums[i];
            }
        }

        for (int i = nums.length - 1; i >= nums.length - zeroSize; i--) {
            nums[i] = 0;
        }
    }

    @Test
    public void test() {

        int[] nums = {0, 1, 0, 3, 12};

        moveZeroes(nums);

        PrintUtils.printArray(nums);
    }

    @Test
    public void test2() {

        int[] nums = {0, 1, 0};

        moveZeroes(nums);

        PrintUtils.printArray(nums);
    }
}
