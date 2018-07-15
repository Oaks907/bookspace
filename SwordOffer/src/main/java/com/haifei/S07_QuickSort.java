package com.haifei;

import java.security.InvalidParameterException;

/**
 * Create by haifei on 9/7/2018.
 */
public class S07_QuickSort {


  private void quickSort(int[] data) throws Exception {
    quickSort(data, 0, data.length - 1);
  }


  private void quickSort(int[] data, int start, int end) throws Exception {
      if (start < end) {
        int partition = partition(data, start, end);
        for (int i = 0; i < data.length; i++) {
          System.out.print(data[i] + " ");
        }
        System.out.println();
        quickSort(data, start, partition - 1 );
        quickSort(data, partition + 1, end);
      }
  }


  int partition(int[] data, int start, int end) throws Exception {

    if (null == data || end < start || start < 0 ) {
      throw new Exception("Invalid Param");
    }
    int tmp = data[end];
    while (start < end) {
      while (start < end && data[start] < tmp) {
        start++;
      }
      if (start < end) {
        data[end--] = data[start];
      }
      while (start < end && data[end] > tmp) {
        end--;
      }
      if (start < end) {
        data[start++] = data[end];
      }

    }
    data[end] = tmp;
    return end;
  }

  private void swap(int[] data, int index1, int index2) {
    int tmp = data[index1];
    data[index1] = data[index2];
    data[index2] = tmp;
  }

  public static void main(String[] args) throws Exception {
    int[] data = {37, 40, 38, 42, 461, 5, 7, 9, 12};
    S07_QuickSort quickSort = new S07_QuickSort();
    quickSort.quickSort(data);
    for (int datum : data) {
      System.out.print(datum + " ");
    }
  }
}
