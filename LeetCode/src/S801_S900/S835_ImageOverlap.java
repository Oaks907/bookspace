package S801_S900;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/image-overlap/
 * Create by haifei on 22/10/2019 10:40 PM.
 */
public class S835_ImageOverlap {

  public int largestOverlap(int[][] A, int[][] B) {

    int rows = A.length;
    int cols = A[0].length;

    List<int[]> aList = new ArrayList<>();
    List<int[]> bList = new ArrayList<>();

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (A[i][j] == 1) {
          aList.add(new int[]{i, j});
        }
        if (B[i][j] == 1) {
          bList.add(new int[]{i, j});
        }
      }
    }

    Map<String, Integer> map = new HashMap<>();

    for (int[] aArr : aList) {
      for (int[] bArr : bList) {
        String key = (aArr[0] - bArr[0]) + " " + (aArr[1] - bArr[1]);
        map.put(key, map.getOrDefault(key, 0) + 1);
      }
    }

    int result = 0;
    for (int count : map.values()) {
      result =  Math.max(result, count);
    }

    return result;
  }

  @Test
  public void test() {
    int[][] aArr = {{1, 1, 0},
      {0, 1, 0},
      {0, 1, 0}};

    int[][] bArr = {{0, 0, 0},
      {0, 1, 1},
      {0, 0, 1}};

    int result = largestOverlap(aArr, bArr);

    Assert.assertEquals(3, result);
  }

  @Test
  public void test2() {
    int[][] aArr = {{0, 0, 0}, {1, 0, 0}, {1, 0, 0}};

    int[][] bArr = {{1, 0, 0}, {1, 1, 1}, {0, 0, 1}};

    int result = largestOverlap(aArr, bArr);

    Assert.assertEquals(2, result);
  }
}
