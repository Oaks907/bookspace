package S901_S1000;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/minimum-increment-to-make-array-unique/
 * Create by haifei on 18/1/2020 10:29 PM.
 */
public class S945_MinimumIncrementtoMakeArrayUnique {

  public int minIncrementForUnique(int[] A) {

    List<Integer> notUnique = new ArrayList<>();
    Set<Integer> uniqueSet = new HashSet<>();

    int minUniqueVal = Integer.MAX_VALUE;
    for (int i = 0; i < A.length; i++) {
      if (uniqueSet.contains(A[i])) {
        notUnique.add(A[i]);
      } else {
        minUniqueVal = Math.min(minUniqueVal, A[i]);
        uniqueSet.add(A[i]);
      }
    }

    Collections.sort(notUnique);

    int result = 0;
    for (Integer item : notUnique) {

      if (item < minUniqueVal) {
        result += (minUniqueVal + 1 - item);
        item = minUniqueVal + 1;
      }

      while (uniqueSet.contains(item)) {
        result++;
        item++;
        minUniqueVal = item;
      }
      uniqueSet.add(item);
    }

    return result;
  }

  public int minIncrementForUnique2(int[] A) {

    Arrays.sort(A);

    int res = 0, need = 0;
    for (int item : A) {
      res += Math.max(need - item, 0);
      need = Math.max(item, need) + 1;
    }

    return res;
  }

  @Test
  public void test2() {

    int[] arr = {1, 3, 0, 3, 0};

    int result = minIncrementForUnique(arr);

    Assert.assertEquals(3, result);

    result = minIncrementForUnique2(arr);

    Assert.assertEquals(3, result);
  }

  @Test
  public void test() {

    int[] arr = {1, 2, 2};

    int result = minIncrementForUnique(arr);

    Assert.assertEquals(1, result);
  }

  @Test
  public void test1() {

    int[] arr = {3, 2, 1, 2, 1, 7};

    int result = minIncrementForUnique(arr);

    Assert.assertEquals(6, result);
  }
}
