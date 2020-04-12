package contest.contest_184;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 12/4/2020 10:52 AM.
 */
public class QueriesonaPermutationWithKey {

    public int[] processQueries(int[] queries, int m) {

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            queue.add(i + 1);
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < queries.length; i++) {
            int val = queries[i];
            int index = queue.indexOf(val);

            list.add(index);
            queue.remove(index);
            queue.addFirst(val);
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    @Test
    public void test() {
        int[] arr = {3, 1, 2, 1};

        int[] result = processQueries(arr, 5);

        PrintUtils.printArray(result);
    }
}
