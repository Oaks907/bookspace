package S1201_S1300;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Create by haifei on 10/11/2019 11:07 PM.
 */
public class S1252_CellswithOddValuesinMatrix {

  public int oddCells(int n, int m, int[][] indices) {
    Set<Integer> rows = new HashSet();
    Set<Integer> cols = new HashSet();
    for (int[] ind : indices) {
      if (rows.contains(ind[0])) {
        rows.remove(ind[0]);
      } else {
        rows.add(ind[0]);
      }
      if (cols.contains(ind[1])) {
        cols.remove(ind[1]);
      } else {
        cols.add(ind[1]);
      }
    }

    int count = 0;
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        boolean a = rows.contains(i);
        boolean b = cols.contains(j);
        if (a && b) continue;
        if (a || b) ++count;
      }
    }

    return count;
  }

  @Test
  public void test() {
    int[][] arr = {{0, 0, 0}, {0, 0, 0}};

    System.out.println(oddCells(2, 3, arr));
  }
}
