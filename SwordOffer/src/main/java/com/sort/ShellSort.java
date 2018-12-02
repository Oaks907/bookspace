package com.sort;

import com.utils.ArrayUtil;
import jdk.nashorn.tools.Shell;

import javax.swing.*;

/**
 * Create by haifei on 2/12/2018 7:26 PM.
 * 平均时间复杂度 o(nlog2 n)
 * 最好时间复杂度 o(n) 最坏 o(n^2)
 * 空间复杂度 o(1)
 * 不稳定排序
 */
public class ShellSort {

  public void shellSort(int[] nums) {

    int temp;

    for (int increment = nums.length; increment > 0; increment /= 2) {
      for (int i = increment; i < nums.length; i++) {
        temp = nums[i];
        int j = i - increment;
        for (; j >= 0 && nums[j] > temp; j -= increment) {
          nums[j + increment] = nums[j];
        }
        nums[j + increment] = temp;
      }
    }
  }

  public static void main(String[] args) {
    int[] numbers = {10, 20, 15, 0, 6, 7, 2, 1, -5, 55};

    new ShellSort().shellSort(numbers);

    ArrayUtil.print(numbers);
  }
}
