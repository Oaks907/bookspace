package S301_400;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 8/9/2020 10:29 AM.
 */
public class S347_TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer orDefault = map.getOrDefault(num, 0);
            map.put(num, orDefault + 1);
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });

        for (Integer item : map.keySet()) {
            int size = priorityQueue.size();
            if (size < k) {
                priorityQueue.add(item);
            } else if (map.get(item) > map.get(priorityQueue.peek())) {
                priorityQueue.remove();
                priorityQueue.add(item);
            }
        }

        // 取出最小堆中的元素
        int[] res = new int[k];
        int index = 0;
        while (!priorityQueue.isEmpty()) {
            res[index++] = priorityQueue.remove();
        }

        return res;
    }

    @Test
    public void test() {
        int[] arr = {1, 1, 1, 2, 2, 3};
        int[] result = topKFrequent(arr, 2);

        PrintUtils.printArray(result);
    }
}
