package S901_S1000;

import utils.PrintUtils;

/**
 * Create by haifei on 20/1/2019 10:46 AM.
 */
public class S977_SquaresofSortedArray {

  public int[] sortedSquares(int[] A) {
    if (null == A) {
      return A;
    }

    for (int i = 0; i < A.length; i++) {
      A[i] = A[i] * A[i];
    }

    quickSort(A, 0, A.length - 1);

    return A;
  }

  private void quickSort(int[] A, int start, int end) {
    if (start < end) {
      int middle = getMiddle(A, start, end);
      quickSort(A, start, middle - 1);
      quickSort(A, middle + 1, end);
    }
  }

  private int getMiddle(int[] A, int left, int right) {
    int value = A[right];

    while (left < right) {
      while (left < right && A[left] <= value) {
        left++;
      }

      A[right] = A[left];

      while (left < right && A[right] >= value) {
        right--;
      }

      A[left] = A[right];
    }

    A[left] = value;

    return left;
  }

  public static void main(String[] args) {

    final S977_SquaresofSortedArray sortedArray = new S977_SquaresofSortedArray();

    int[] A = {-4, -1, 0, 3, 10};
    System.out.println(sortedArray.sortedSquares(A));
    PrintUtils.printArray(A);

    A = new int[]{-7, -3, 2, 3, 11};
    System.out.println(sortedArray.sortedSquares(A));
    PrintUtils.printArray(A);
  }
}
