package com.sort;

import com.utils.ArrayUtil;

/**
 * Create by haifei on 2/12/2018 6:51 PM.
 * 平均时间复杂度 o(n^2)
 * 最好时间复杂度 o(n) 最坏 o(n^2)
 * 空间复杂度 o(1)
 * 稳定排序
 */
public class InsertSort {

  public void insertSort(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      int key = nums[i];
      int j = i;
      for (; j > 0 && nums[j - 1] > key; j--) {
        nums[j] = nums[j - 1];
      }
      nums[j] = key;
    }
  }

  public static void main(String[] args) {
    int[] numbers = {10, 20, 15, 0, 6, 7, 2, 1, -5, 55};

    new InsertSort().insertSort(numbers);

    ArrayUtil.print(numbers);
  }
}
