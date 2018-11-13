package com.haifei;

/**
 * Create by haifei on 13/11/2018 8:40 PM.
 * 排序数组中某个数字出现的个数
 */
public class S38_numberOfK {

  public static int getFirstK(int[] nums, int k) {

    int start = 0;
    int end = nums.length - 1;
    int index = -1;

    //二分查找法
    while (start < end) {
      int middle = (start + end) / 2;
      if (nums[middle] == k) {
        index = middle;
        break;
      } else if (nums[middle] > k) {
        end = middle;
      } else {
        start = middle;
      }
    }

    if (index < 0) {
      return index;
    }

    int numStart = 0, numEnd = 0;
    for (int i = index; i < nums.length; i++) {
      if (nums[i] == k) {
        numEnd = i;
      }
    }
    for (int i = index; i >= 0; i--) {
      if (nums[i] == k) {
        numStart = i;
      }
    }

    return numEnd - numStart + 1;
  }

  public static void main(String[] args) {
    int[] nums = {2, 2, 3, 3, 3, 3, 5};
    System.out.println(getFirstK(nums, 3));
  }
}
