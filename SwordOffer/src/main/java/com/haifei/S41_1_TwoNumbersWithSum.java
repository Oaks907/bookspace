package com.haifei;

/**
 * Create by haifei on 14/11/2018 8:10 AM.
 * 递增数组中，和为sum的两个数字
 */
public class S41_1_TwoNumbersWithSum {

  boolean findNumberWithSum(int[] data, int sum) {
    int num1 = 0, num2 = 0;
    boolean isFound = false;

    int left = 0;
    int right = data.length - 1;

    while (left < right) {

      int curSum = data[left] + data[right];
      if (curSum == sum) {
        num1 = left;
        num2 = right;
        isFound = true;
        break;
      } else if (curSum > sum) {
        right--;
      } else if (curSum < sum) {
        left--;
      }
    }

    return isFound;
  }
}
