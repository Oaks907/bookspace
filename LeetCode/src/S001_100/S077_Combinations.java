package S001_100;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Create by haifei on 8/9/2020 10:20 AM.
 */
public class S077_Combinations {

    public List<List<Integer>> combine(int n, int k) {

        helper(1, n, k, new ArrayList<>());

        return result;
    }

    List<List<Integer>> result = new ArrayList<>();

    public void helper(int start, int end, int k, List<Integer> list) {
        if (k == 0) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i <= end; i++) {
            list.add(i);
            helper(i + 1, end, k - 1, list);
            list.remove(list.size() - 1);
        }
    }

    @Test
    public void test() {
        List<List<Integer>> result = combine(4, 3);
        System.out.println(result);
    }
}
