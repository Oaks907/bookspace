package com.sort;

import com.utils.ArrayUtil;

/**
 * Create by haifei on 2/12/2018 5:06 PM.
 * 平均时间复杂度 o(nlog2 n)
 * 最好时间复杂度 o(nlog2 n) 最坏 o(n^2)
 * 空间复杂度 o(nlog2 n)
 * 非稳定排序
 */
public class QuickSort {

  public void quickSort(int[] nums) {
    if (nums == null || 0 == nums.length) {
      return;
    }
    quickSortArr(nums, 0, nums.length - 1);
  }

  public void quickSortArr(int[] nums, int left, int right) {
    if (left >= right) {
      return;
    }

    int middle = getMiddle(nums, left, right);
    quickSortArr(nums, left, middle - 1);
    quickSortArr(nums, middle + 1, right);
  }

  public int getMiddle(int[] nums, int left, int right) {

    int key = nums[right];

    while (left < right) {
      while (left < right && nums[left] < key) {
        left++;
      }

      nums[right] = nums[left];

      while (left < right && nums[right] >= key) {
        right--;
      }

      nums[left] = nums[right];
    }
    nums[left] = key;
    return left;
  }

  public static void main(String[] args) {
    int[] arr = {5, 4, 3, 2, 1};

    new QuickSort().quickSort(arr);

    ArrayUtil.print(arr);
  }
}
