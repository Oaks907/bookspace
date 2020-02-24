package S401_500;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/circular-array-loop/
 * <p>
 * Create by haifei on 24/2/2020 2:54 PM.
 */
public class S457_CircularArrayLoop {

    /**
     * 快慢指针
     *
     * @param nums
     * @return
     */
    public boolean circularArrayLoop(int[] nums) {

        boolean isCircularArrayLoop = false;

        for (int i = 0; i < nums.length; i++) {

            Integer slow = i;
            Integer fast = getNext(nums, 0, i);
            int dir = nums[i];

            while (slow != null && fast != null && slow != fast) {
                slow = getNext(nums, dir, slow);
                fast = getNext(nums, dir, getNext(nums, dir, fast));
            }

            if (slow != null && fast == slow) {
                isCircularArrayLoop = true;
                break;
            }
        }

        return isCircularArrayLoop;
    }

    /**
     * 获取下一个位置的数据
     *
     * @param nums 操作的数组
     * @param dir  控制数组只向一个方向查找
     * @param pos  当前所在位置索引
     * @return
     */
    public Integer getNext(int[] nums, int dir, Integer pos) {

        if (pos == null) {
            return null;
        }

        if (dir * nums[pos] < 0) {
            return null;
        }

        Integer next = ((pos + nums[pos]) % nums.length >= 0) ? (pos + nums[pos]) % nums.length
                               : nums.length + (pos + nums[pos]) % nums.length;

        if (next == pos) {
            return null;
        }

        return next;
    }

    @Test
    public void test() {
        int[] arr = {2, -1, 1, 2, 2};

        boolean result = circularArrayLoop(arr);

        Assert.assertTrue(result);
    }

    @Test
    public void test1() {
        int[] arr = {-2, 1, -1, -2, -2};

        boolean result = circularArrayLoop(arr);

        Assert.assertFalse(result);
    }

    @Test
    public void test2() {
        int[] arr = {2, -1, 1, -2, -2};

        boolean result = circularArrayLoop(arr);

        Assert.assertFalse(result);
    }

    @Test
    public void test3() {
        int[] arr = {-1};

        boolean result = circularArrayLoop(arr);

        Assert.assertFalse(result);
    }
}
