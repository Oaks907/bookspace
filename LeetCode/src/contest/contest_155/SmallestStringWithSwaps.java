package contest.contest_155;

import org.junit.Test;
import utils.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by haifei on 22/9/2019 11:29 AM.
 */
public class SmallestStringWithSwaps {

  private int[] ids;
  private Map<Integer, int[]> idToGroup;

  public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
    this.ids = new int[s.length()];
    this.idToGroup = new HashMap<>();

    StringBuilder stringBuilder = new StringBuilder();

    // init
    for (int i = 0; i < s.length(); i++) {
      ids[i] = i;
    }

    for (List<Integer> p : pairs) {
      union(p.get(0), p.get(1));
    }

    PrintUtils.printArray(ids);

    for (int i = 0; i < s.length(); i++) {
      int iId = find(i);
      if (!this.idToGroup.containsKey(iId)) {
        idToGroup.put(iId, new int[26]);
      }
      idToGroup.get(iId)[s.charAt(i) - 'a']++;
    }

    for (int i = 0; i < s.length(); i++) {
      int iId = find(i);
      int[] group = this.idToGroup.get(iId);

      for (int j = 0; j < group.length; j++) {
        if (group[j] > 0) {
          stringBuilder.append((char) ('a' + j));
          group[j]--;
          break;
        }
      }
    }
    return stringBuilder.toString();
  }

  private void union(int x, int y) {
    int xId = find(x);
    int yId = find(y);

    if (xId != yId) {
      ids[xId] = yId;
    }
  }

  private int find(int x) {
    if (ids[x] != x) {
      ids[x] = find(ids[x]);
    }
    return ids[x];
  }

  @Test
  public void test() {
    List<List<Integer>> list = new ArrayList<>();
    list.add(Arrays.asList(0, 3));
    list.add(Arrays.asList(1, 2));

    String result = smallestStringWithSwaps("dcab", list);
    System.out.println(result);
  }
}
