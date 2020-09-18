package S001_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 18/9/2020 8:54 AM.
 */
public class S047_PermutationsII {

    boolean[] visited;
    List<List<Integer>> result;

    public List<List<Integer>> permuteUnique(int[] nums) {

        visited = new boolean[nums.length];
        result = new ArrayList<>();

        Arrays.sort(nums);

        recursion(nums, 0, new ArrayList<>());

        return result;
    }

    public void recursion(int[] nums, int index, List<Integer> list) {

        if (index >= nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (visited[i] || (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1])) {
                continue;
            }

            visited[i] = true;
            list.add(nums[i]);
            recursion(nums, index + 1, list);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }

    @Test
    public void test2() {

        int[] nums = {1, 1, 1};

        List<List<Integer>> result = permuteUnique(nums);

        System.out.println(result);

        Assert.assertEquals(1, result.size());
    }

    @Test
    public void test() {

        int[] nums = {1, 1, 2};

        List<List<Integer>> result = permuteUnique(nums);

        System.out.println(result);

        Assert.assertEquals(3, result.size());
    }

    @Test
    public void test1() {

        int[] nums = {1, 3, 2};

        List<List<Integer>> result = permuteUnique(nums);

        System.out.println(result);

        Assert.assertEquals(6, result.size());
    }
}
