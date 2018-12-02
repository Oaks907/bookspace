package com.sort;

import com.utils.ArrayUtil;

/**
 * Create by haifei on 2/12/2018 5:28 PM.
 * 平均时间复杂度 o(n^2)
 * 最好时间复杂度 o(n^2) 最坏 o(n)
 * 空间复杂度 o(1)
 * 稳定排序
 */
public class SelectSort {

  public void selectSort(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      int lowestIndex = i;

      for (int j = i; j < nums.length; j++) {
        if (nums[j] < nums[lowestIndex]) {
          lowestIndex = j;
        }
      }
      if (lowestIndex != i) {
        ArrayUtil.swap(nums, i, lowestIndex);
      }
    }
  }

  public static void main(String[] args) {

    int[] numbers = {10,20,15,0,6,7,2,1,-5,55};

    new SelectSort().selectSort(numbers);

    ArrayUtil.print(numbers);
  }
}
