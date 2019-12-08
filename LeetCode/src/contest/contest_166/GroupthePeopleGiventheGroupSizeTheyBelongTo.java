package contest.contest_166;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by haifei on 8/12/2019 10:52 AM.
 */
public class GroupthePeopleGiventheGroupSizeTheyBelongTo {

  public List<List<Integer>> groupThePeople(int[] groupSizes) {

    Map<Integer, List<Integer>> map = new HashMap<>();

    for (int i = 0; i < groupSizes.length; i++) {
      List<Integer> orDefault = map.getOrDefault(groupSizes[i], new ArrayList());
      orDefault.add(i);
      map.put(groupSizes[i], orDefault);
    }

    List<List<Integer>> result = new ArrayList<>();
    for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
      int key = entry.getKey();
      List<Integer> value = entry.getValue();

      if (value.size() > key) {
        int count = 0;
        while (count * key < value.size()) {
          result.add(new ArrayList<>(value.subList(count * key, (count + 1) * key)));
          count++;
        }
      } else {
        result.add(new ArrayList<>(value));
      }
    }
    return result;
  }

  @Test
  public void test() {
    int[] groupSize = {3, 3, 3, 3, 3, 1, 3};

    List<List<Integer>> result = groupThePeople(groupSize);

    System.out.println(result);
  }


  @Test
  public void test1() {
    int[] groupSize = {2, 1, 3, 3, 3, 2};

    List<List<Integer>> result = groupThePeople(groupSize);

    System.out.println(result);
  }
}
