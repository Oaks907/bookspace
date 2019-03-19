package com.utils;

import java.util.Arrays;

/**
 * Create by haifei on 2/12/2018 4:54 PM.
 */
public class ArrayUtil {

  public static void print(int[] arr) {
    Arrays.stream(arr).forEach(item -> {
      System.out.print(item + " ");
    });
    System.out.println();
  }

  public static void swap(int[] arr, int left, int right) {
    int temp = arr[left];
    arr[left] = arr[right];
    arr[right] = temp;
  }

  public static void main(String[] args) {

  }
}
