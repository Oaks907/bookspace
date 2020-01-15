package S301_400;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Create by haifei on 14/1/2020 11:11 PM.
 */
public class S380_InsertDeleteGetRandom {

  HashMap<Integer, Integer> valToInd;
  List<Integer> list;

  /**
   * Initialize your data structure here.
   */
  public S380_InsertDeleteGetRandom() {
    valToInd = new HashMap<>();
    list = new ArrayList<>();
  }

  /**
   * Inserts a value to the set. Returns true if the set did not already contain the specified
   * element.
   */
  public boolean insert(int val) {
    if (valToInd.containsKey(val)) {
      return false;
    }
    list.add(val);
    valToInd.put(val, list.size() - 1);
    return true;
  }

  /**
   * Removes a value from the set. Returns true if the set contained the specified element.
   */
  public boolean remove(int val) {
    int index = valToInd.getOrDefault(val, -1);
    if (index == -1) {
      return false;
    }
    Collections.swap(list, index, list.size() - 1);
    int swapVal = list.get(index);
    valToInd.put(swapVal, index);

    valToInd.remove(val);
    list.remove(list.size() - 1);
    return true;
  }

  /**
   * Get a random element from the set.
   */
  public int getRandom() {
    int max = list.size();
    int min = 0;
    int ind = (int) (Math.random() * (max - min) + min);
    return list.get(ind);
  }
}
