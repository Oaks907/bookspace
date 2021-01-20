package contest.contest_224;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 17/1/2021 10:46 AM.
 */
public class M3_ {

    public int tupleSameProduct(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int key = nums[i] * nums[j];
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }

        int ans = 0;
        for (Integer value : map.values()) {
            ans += value * (value - 1) / 2;
        }

        return ans * 8;
    }

    @Test
    public void test() {
        int[] arr = {2, 3, 4, 6};

        int result = tupleSameProduct(arr);

        Assert.assertEquals(8, result);
    }

    @Test
    public void test1() {
        int[] arr = {1, 2, 4, 5, 10};

        int result = tupleSameProduct(arr);

        Assert.assertEquals(16, result);
    }

    @Test
    public void test2() {
        int[] arr = {2, 3, 4, 6, 8, 12};

        int result = tupleSameProduct(arr);

        Assert.assertEquals(40, result);
    }

    @Test
    public void test3() {
        int[] arr =
                {340, 7, 28, 270, 11, 320, 42, 360, 36, 126, 102, 220, 26, 52, 252, 165, 32, 35, 135, 162, 91, 14, 85,
                        272, 228, 27, 50, 209, 63, 133, 84, 45, 90, 304, 19, 44, 266, 144, 176, 2, 216, 221, 100, 108,
                        75, 40, 33, 306, 95, 204, 24, 260, 39, 288, 30, 20, 96, 114, 240, 117, 136, 285, 300, 10, 119,
                        171, 38, 200, 128, 238, 156, 152, 12, 18, 168, 153, 80, 15, 180, 190, 323, 234, 280, 112, 182,
                        132, 170, 8, 120, 208, 66, 210, 130, 98, 198, 6, 65, 57, 17, 342};

        int result = tupleSameProduct(arr);

        Assert.assertEquals(52064, result);
    }

}
