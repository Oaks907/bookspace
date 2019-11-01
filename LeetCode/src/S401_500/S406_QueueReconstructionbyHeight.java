package S401_500;

import org.junit.Test;
import utils.PrintUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Create by haifei on 1/11/2019 11:31 PM.
 */
public class S406_QueueReconstructionbyHeight {

  public int[][] reconstructQueue(int[][] people) {

    Arrays.sort(people, (n1, n2) -> n1[0] == n2[0] ? n1[1] - n2[1] : n2[0] - n1[0]);

    List<int[]> result = new LinkedList<>();

    for (int[] p : people) {
      result.add(p[1], p);
    }


    return result.toArray(new int[people.length][2]);
  }

  @Test
  public void test() {
    int[][] arr = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};

    int[][] ints = reconstructQueue(arr);
  }

}
