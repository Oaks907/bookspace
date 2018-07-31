package com.haifei;

/**
 * Create by haifei on 31/7/2018.
 */
public class S14_ReorderArray {

  private int[] reorder(int[] array) {
    if (null == array || array.length == 0) {
      throw new IllegalArgumentException();
    }

    int left = 0;
    int right = array.length - 1;

    while (left < right) {
      while (left < right && isOddNumber(array[left])) {
        left++;
      }
      while (left < right && !isOddNumber(array[right])) {
        right--;
      }
      if (left < right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
      }
    }

    return array;
  }

  private boolean isOddNumber(int number) {
    return (number & 1) == 1;
  }

  public static void main(String[] args) {
    int array[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    array = new S14_ReorderArray().reorder(array);
    for (int i : array) {
      System.out.print(i + " ");
    }
  }
}
