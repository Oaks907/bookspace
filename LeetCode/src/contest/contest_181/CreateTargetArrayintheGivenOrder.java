package contest.contest_181;

import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 22/3/2020 10:35 AM.
 */
public class CreateTargetArrayintheGivenOrder {

    public int[] createTargetArray(int[] nums, int[] index) {

        int len = nums.length;
        int[] result = new int[len];

        for (int i = 0; i < len; i++) {
            result[i] = -1;
        }

        int lastMaxIndex = 0;
        for (int i = 0; i < len; i++) {
            int val = nums[i];
            int indexForVal = index[i];

            lastMaxIndex = Math.max(lastMaxIndex, indexForVal);

            if (result[indexForVal] == -1) {
                result[indexForVal] = val;
            } else {
                for (int k = len - 2; k >= indexForVal; k--) {
                    result[k + 1] = result[k];
                }
                result[indexForVal] = val;
            }
        }

        return result;
    }

    @Test
    public void test() {
        int[] arr = {0, 1, 2, 3, 4};
        int[] index = {0, 1, 2, 2, 1};

        // [0,4,1,3,2]
        int[] result = createTargetArray(arr, index);

        PrintUtils.printArray(result);
    }
}
