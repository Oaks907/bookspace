package S1201_S1300;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Create by haifei on 14/1/2020 10:04 PM.
 */
public class S1296_DivideArrayinSetsofKConsecutiveNumbers {

  public boolean isPossibleDivide(int[] nums, int k) {

    if (nums.length % k != 0) {
      return false;
    }

    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    PriorityQueue<Map.Entry<Integer, Integer>> queue =
      new PriorityQueue<>((a, b) -> a.getKey() != b.getKey() ? a.getKey() - b.getKey() :
        b.getKey() - a.getKey());

    queue.addAll(map.entrySet());

    while (!queue.isEmpty()) {

      ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>();
      int start = queue.peek().getKey() - 1;
      int count = k;
      while (count-- > 0 && !queue.isEmpty()) {
        Map.Entry<Integer, Integer> entry = queue.poll();
        entry.setValue(entry.getValue() - 1);
        list.add(entry);
        if (start + 1 == entry.getKey()) {
          start = entry.getKey();
        } else {
          return false;
        }
      }

      for (Map.Entry<Integer, Integer> item : list) {
        if (item.getValue() > 0) {
          queue.add(item);
        }
      }
      if (queue.isEmpty()) {
        return true;
      }
    }

    return true;
  }

  @Test
  public void test() {
    int[] arr = {3, 2, 1, 2, 3, 4, 3, 4, 5, 9, 10, 11};

    boolean result = isPossibleDivide(arr, 3);

    Assert.assertTrue(result);
  }

  @Test
  public void test1() {

    int[] arr = {1, 2, 3, 4};

    boolean result = isPossibleDivide(arr, 3);

    Assert.assertFalse(result);
  }
}
