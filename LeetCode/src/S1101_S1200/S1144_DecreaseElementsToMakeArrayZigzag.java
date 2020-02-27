package S1101_S1200;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 27/2/2020 12:04 PM.
 */
public class S1144_DecreaseElementsToMakeArrayZigzag {

    /**
     * @param nums
     * @return
     */
    public int movesToMakeZigzag_self(int[] nums) {

        int oddNeedChange = 0;
        int evenNeedChange = 0;

        int[] oddArr = nums.clone();
        int[] evenArr = nums.clone();
        int len = nums.length;

        for (int i = 1; i < oddArr.length; i = i + 2) {

            if (oddArr[i] <= oddArr[i - 1]) {
                oddNeedChange += oddArr[i - 1] - oddArr[i] + 1;
            }
            if (i + 1 < len && oddArr[i] <= oddArr[i + 1]) {
                oddNeedChange += oddArr[i + 1] - oddArr[i] + 1;
                oddArr[i + 1] = oddArr[i] - 1;
            }
        }

        for (int i = 0; i < evenArr.length; i = i + 2) {

            if (i != 0 && evenArr[i] <= evenArr[i - 1]) {
                evenNeedChange += evenArr[i - 1] - evenArr[i] + 1;
            }
            if (i + 1 < len && evenArr[i] <= evenArr[i + 1]) {
                evenNeedChange += evenArr[i + 1] - evenArr[i] + 1;
                evenArr[i + 1] = evenArr[i] - 1;
            }
        }

        return Math.min(evenNeedChange, oddNeedChange);
    }

    public int movesToMakeZigzag(int[] nums) {

        int[] res = new int[2];
        int left, right, len = nums.length;

        for (int i = 0; i < len; i++) {
            left = i > 0 ? nums[i - 1] : 1001;
            right = i < len - 1 ? nums[i + 1] : 1001;

            res[i % 2] += Math.max(0, nums[i] - Math.min(left, right) + 1);
        }

        return Math.min(res[0], res[1]);
    }

    @Test
    public void test3() {

        int[] arr = {7, 4, 8, 9, 7, 7, 5};

        int result = movesToMakeZigzag(arr);

        Assert.assertEquals(6, result);
    }

    @Test
    public void test2() {

        int[] arr = {1, 2, 1};

        int result = movesToMakeZigzag(arr);

        Assert.assertEquals(0, result);
    }

    @Test
    public void test() {

        int[] arr = {1, 2, 3};

        int result = movesToMakeZigzag(arr);

        Assert.assertEquals(2, result);
    }

    @Test
    public void test1() {

        int[] arr = {9, 6, 1, 6, 2};

        int result = movesToMakeZigzag(arr);

        Assert.assertEquals(4, result);
    }
}
