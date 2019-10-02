package S901_S1000;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/pancake-sorting/
 * Create by haifei on 28/9/2019 9:43 PM.
 */
public class S969_PancakeSorting {

  public List<Integer> pancakeSort(int[] A) {

    List<Integer> result = new ArrayList<>();

    for (int x = A.length, i; x > 0; x--) {
      for (i = 0; A[i] != x; i++) ;
      reverse(A, i + 1);
      result.add(i + 1);
      reverse(A, x);
      result.add(x);
    }

    return result;
  }

  /**
   * 反转数组 0 --- (k - 1)
   *
   * @param A 数据
   * @param k 位置
   */
  private void reverse(int[] A, int k) {
    for (int i = 0, j = k - 1; i < j; i++, j--) {
      int temp = A[i];
      A[i] = A[j];
      A[j] = temp;
    }
  }

  @Test
  public void test() {
    int[] A = {3, 2, 4, 1};
    List<Integer> result = pancakeSort(A);

    System.out.println(result);
  }
}
