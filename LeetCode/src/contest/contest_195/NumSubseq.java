package contest.contest_195;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 28/6/2020 11:17 AM.
 */
public class NumSubseq {

    /**
     * 内存超出限制
     */
    public int numSubseq_MLE(int[] nums, int target) {

        Arrays.sort(nums);
        recursion(nums, 0, target, new ArrayList<>());

        int count = 0;

        for (List<Integer> list : result) {
            if (list.get(0) + list.get(list.size() - 1) <= target) {
                count++;
            }
        }

        return count;
    }

    public List<List<Integer>> result = new ArrayList<>();

    public void recursion(int[] nums, int curIndex, int target, List<Integer> list) {
        if (!list.isEmpty()) {
            result.add(new ArrayList<>(list));
        }
        if (curIndex >= nums.length) {
            return;
        }
        if (nums[curIndex] > target) {
            return;
        }
        for (int i = curIndex; i < nums.length; i++) {
            list.add(nums[i]);
            recursion(nums, i + 1, target, list);
            list.remove(list.size() - 1);
        }
    }

    /*******/

    public int numSubseq(int[] nums, int target) {

        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        int res = 0;
        while (i <= j) {
            while (i <= j && nums[i] + nums[j] > target) {
                --j;
            }
            if (i > j) {
                break;
            }
            res += quickPow(2, j - i);
            res %= MOD;
            ++i;
        }
        return res;
    }

    int MOD = (int) (1e9 + 7);

    public long quickPow(long a, long b) {
        long k = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                k = (k * a) % MOD;
            }
            b = b >> 1;
            a = (a * a) % MOD;
        }

        return k;
    }

    @Test
    public void test6() {
        quickPow(2, 5);
    }

    @Test
    public void test1() {
        int[] arr = {3, 5, 6, 7};

        int result = numSubseq(arr, 9);

        Assert.assertEquals(4, result);
    }

    @Test
    public void test2() {
        int[] arr = {3, 3, 6, 8};

        int result = numSubseq(arr, 10);

        Assert.assertEquals(6, result);
    }

    @Test
    public void test3() {
        int[] arr = {2, 3, 3, 4, 6, 7};

        int result = numSubseq(arr, 12);

        Assert.assertEquals(61, result);
    }

    @Test
    public void test4() {
        int[] arr = {5, 2, 4, 1, 7, 6, 8};

        int result = numSubseq(arr, 16);

        Assert.assertEquals(127, result);
    }
}
