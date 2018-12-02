package com.sort;

import com.utils.ArrayUtil;

/**
 * Create by haifei on 29/11/2018 11:43 AM.
 * 平均时间复杂度 o(nlog2 n)
 * 最好时间复杂度 o(nlog2 n) 最坏 o(nlog2 n)
 * 空间复杂度 o(1)
 * 不稳定排序
 */
public class HeapSort {

  /**
   * @param array 需要调整的数组
   * @param i     当前调整的数组的节点
   * @param n     需要调整的数组的最大索引，调整范围为 0 - n
   */
  public void heapAdjust(int[] array, int i, int n) {
    int fatherValue = array[i];
    int child;

    for (; getLeftChild(i) < n; i = child) {
      child = getLeftChild(i);

      if (child < n - 1 && array[child] < array[child + 1]) {
        child = child + 1;
      }

      if (array[child] < fatherValue) {
        break;
      } else {
        array[i] = array[child];
      }
    }
    array[i] = fatherValue;
  }

  /**
   * 根据父节点，求出当前父节点的左子节点
   *
   * @param n 父节点的位置
   * @return
   */
  public int getLeftChild(int n) {
    return n * 2 + 1;
  }

  public void swap(int[] array, int a, int b) {
    int temp = array[a];
    array[a] = array[b];
    array[b] = temp;
  }

  public void heapSort(int[] nums) {

    for (int i = nums.length / 2 - 1; i >= 0; i--) {
      heapAdjust(nums, i, nums.length);
    }

    int length = nums.length - 1;

    // 逐步将每个最大值的根节点与末尾元素交换，并且再调整二叉树，使其成为大顶堆
    for (int i = nums.length - 1; i > 0; i--) {
      swap(nums, 0, i); // 将堆顶记录和当前未经排序子序列的最后一个记录交换
      heapAdjust(nums, 0, i); // 交换之后，需要重新检查堆是否符合大顶堆，不符合则要调整
    }
  }

  public static void main(String[] args) {
    int[] numbers = {10, 20, 15, 0, 6, 7, 2, 1, -5, 55};

    new HeapSort().heapSort(numbers);

    ArrayUtil.print(numbers);
  }


}
