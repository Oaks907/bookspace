package com.sort;

import com.utils.ArrayUtil;

/**
 * Create by haifei on 2/12/2018 4:39 PM.
 * 平均时间复杂度 o(n^2)
 * 最好时间复杂度 o(n) 最坏 o(n^2)
 * 空间复杂度 o(1)
 * 稳定排序
 */
public class BubbleSort {

  public void bubbleSort(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = 1; j < nums.length - i; j++) {
        if (nums[j - 1] > nums[j]) {
          ArrayUtil.swap(nums, j - 1, j);
        }
      }
    }
  }

  public static void main(String[] args) {
    int[] arr = {5, 4, 3, 2, 1};

    BubbleSort bubbleSort = new BubbleSort();
    bubbleSort.bubbleSort(arr);

    ArrayUtil.print(arr);
  }
}
