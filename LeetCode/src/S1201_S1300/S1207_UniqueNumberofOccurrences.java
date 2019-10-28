package S1201_S1300;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/unique-number-of-occurrences/
 * Create by haifei on 30/9/2019 8:42 AM.
 */
public class S1207_UniqueNumberofOccurrences {

  public boolean uniqueOccurrences(int[] arr) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
      map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
    }

    Set<Integer> set = new HashSet<>();
    for ( Integer value : map.values()) {
      if (set.contains(value)){
        return false;
      } else {
        set.add(value);
      }
    }

    return true;
  }

  @Test
  public void test() {
    int[] arr = {3, 5, -2, -3, -6, -6};

    System.out.println(uniqueOccurrences(arr));
  }
}
