package S901_S1000;

import org.junit.Test;
import utils.PrintUtils;

/**
 * https://leetcode.com/problems/sort-array-by-parity/
 * Create by haifei on 18/9/2019 12:03 AM.
 */
public class S905_SortArrayByParity {

  public int[] sortArrayByParity(int[] A) {
    int left = 0;
    int right = A.length - 1;

    while (left < right) {
      if (isOdd(A[left])) {
        left++;
        continue;
      }
      if (!isOdd(A[right])) {
        right--;
        continue;
      }
      int temp = A[left];
      A[left] = A[right];
      A[right] = temp;

      left++;
      right--;
    }

    return A;
  }

  public boolean isOdd(int num) {
    return (num % 2) == 0;
  }

  @Test
  public void test() {
    int[] nums = {3, 1, 2, 4};
    int[] result = sortArrayByParity(nums);

    PrintUtils.printArray(result);
  }
}
