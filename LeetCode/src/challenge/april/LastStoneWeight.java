package challenge.april;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 12/4/2020 9:53 PM.
 */
public class LastStoneWeight {

    public int lastStoneWeight(int[] stones) {

        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int stone : stones) {
            queue.add(stone);
        }

        while (!queue.isEmpty() && queue.size() != 1) {
            Integer first = queue.poll();
            Integer second = queue.poll();
            if (first != second) {
                queue.add(first - second);
            }
        }

        return queue.isEmpty() ? 0 : queue.poll();
    }

    @Test
    public void test() {

        int[] stones = {2, 7, 4, 1, 8, 1};

        int result = lastStoneWeight(stones);

        Assert.assertEquals(1, result);
    }
}
