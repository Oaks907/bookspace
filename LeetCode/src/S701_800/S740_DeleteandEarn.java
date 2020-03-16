package S701_800;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 16/3/2020 12:50 PM.
 */
public class S740_DeleteandEarn {

    public int deleteAndEarn_slow(int[] nums) {

        if (null == nums || 0 == nums.length) {
            return 0;
        }

        Map<Integer, Integer> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + nums[i]);
        }

        Integer[] array = map.keySet().toArray(new Integer[0]);
        int[] dp = new int[array.length];
        dp[0] = map.get(array[0]);

        for (int i = 1; i < array.length; i++) {
            int k = array[i];
            if (i == 1) {
                dp[1] = (k - 1 == array[i - 1]) ? Math.max(map.get(k), dp[0]) : map.get(k) + dp[i - 1];
            } else {
                dp[i] = (k - 1 == array[i - 1]) ? Math.max(map.get(k) + dp[i - 2], dp[i - 1]) : map.get(k) + dp[i - 1];
            }
        }

        //        PrintUtils.printArray(dp);

        return dp[array.length - 1];
    }

    public int deleteAndEarn(int[] nums) {

        final Map<Integer, Integer> values = new HashMap<>();
        for (final int num : nums) {
            values.put(num, values.getOrDefault(num, 0) + num);
        }
        int pre = 0, cur = 0;
        for (Integer num : values.keySet()) {
            if (!values.containsKey(num - 1)) {
                pre = cur;
                cur += values.get(num);
            } else {
                int temp = Math.max(pre + values.get(num), cur);
                pre = cur;
                cur = temp;
            }
        }

        return cur;
    }

    @Test
    public void test() {

        int[] arr = {3, 4, 2};

        int result = deleteAndEarn(arr);

        Assert.assertEquals(6, result);
    }

    @Test
    public void test1() {

        int[] arr = {2, 2, 3, 3, 3, 4};

        int result = deleteAndEarn(arr);

        Assert.assertEquals(9, result);
    }
}
