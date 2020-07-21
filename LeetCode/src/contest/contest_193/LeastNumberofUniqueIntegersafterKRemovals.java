package contest.contest_193;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

/**
 * Create by haifei on 14/6/2020 10:38 AM.
 */
public class LeastNumberofUniqueIntegersafterKRemovals {

    public int findLeastNumOfUniqueInts(int[] arr, int k) {

        if (k >= arr.length) {
            return 0;
        }

        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < arr.length; i++) {
            Integer orDefault = map.getOrDefault(arr[i], 0);
            map.put(arr[i], orDefault + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        for (int i = list.size() - 1; i >= 0; i--) {
            Map.Entry<Integer, Integer> entry = list.get(i);
            if (k == entry.getValue()) {
                list.remove(entry);
                k = 0;
                break;
            } else if (k < entry.getValue()) {
                k = 0;
                break;
            } else {
                list.remove(entry);
                k = k - entry.getValue();
            }
        }

        return list.size();
    }

    @Test
    public void test() {
        int[] arr = {4, 3, 1, 1, 3, 3, 2};

        int result = findLeastNumOfUniqueInts(arr, 3);

        System.out.println(result);
    }
}
