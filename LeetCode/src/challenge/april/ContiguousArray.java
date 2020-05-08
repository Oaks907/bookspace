package challenge.april;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 13/4/2020 9:15 PM.
 */
public class ContiguousArray {

    public int findMaxLength(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0, max = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                max = Math.max(max, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }

        return max;
    }

    @Test
    public void test1() {

        int[] nums = {0, 0, 1, 1, 1, 1, 1, 0, 0};
        int result = findMaxLength(nums);

        Assert.assertEquals(4, result);
    }

    @Test
    public void test() {

        int[] nums = {0, 0, 1, 0, 0, 0, 1, 1};
        int result = findMaxLength(nums);

        Assert.assertEquals(6, result);
    }
}
