package com.sort;

import com.utils.ArrayUtil;

/**
 * Create by haifei on 2/12/2018 7:45 PM.
 * 平均时间复杂度 o(nlog2 n)
 * 最好时间复杂度 o(nlog2 n) 最坏 o(nlog2 n)
 * 空间复杂度 o(n)
 * 不稳定排序
 */
public class MergeSort {

  public void sort(int[] nums, int left, int right) {
    int middle = (left + right) / 2;
    if (left < right) {
      sort(nums, left, middle);
      sort(nums, middle + 1, right);
      merge(nums, left, middle, right);
    }
  }

  public void merge(int[] nums, int left, int middle, int right) {
    int[] temp = new int[right - left + 1];
    int index = 0;
    int i = left;
    int j = middle + 1;

    while (i <= middle && j <= right) {
      if (nums[i] < nums[j]) {
        temp[index++] = nums[i++];
      } else {
        temp[index++] = nums[j++];
      }
    }

    while (i <= middle) {
      temp[index++] = nums[i++];
    }

    while (j <= right) {
      temp[index++] = nums[j++];
    }

    for (int k = 0; k < temp.length; k++) {
      nums[left++] = temp[k];
    }
  }

  public static void main(String[] args) {

    int[] numbers = {10, 20, 15, 0, 6, 7, 2, 1, -5, 55};

    new MergeSort().sort(numbers, 0, numbers.length - 1);

    ArrayUtil.print(numbers);
  }


}
