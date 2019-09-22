package contest.contest_155;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create by haifei on 22/9/2019 10:31 AM.
 */
public class MinimumAbsoluteDifference {

  public List<List<Integer>> minimumAbsDifference(int[] arr) {

    List<List<Integer>> result = new ArrayList<>();
    if (arr == null || arr.length == 0) {
      return result;
    }
    Arrays.sort(arr);

    int basDiff = Integer.MAX_VALUE;
    for (int i = 1; i < arr.length; i++) {
      ArrayList<Integer> list = new ArrayList<>();
      list.add(arr[i - 1]);
      list.add(arr[i]);

      int abs = Math.abs(arr[i] - arr[i - 1]);
      if (abs < basDiff) {
        result.clear();
        result.add(list);
        basDiff = abs;
      } else if (abs == basDiff) {
        result.add(list);
      }
    }

    return result;
  }

  @Test
  public void test() {
    int[] arr = {3,8,-10,23,19,-4,-14,27};
    List<List<Integer>> result = minimumAbsDifference(arr);

    System.out.println(result);
  }
}
