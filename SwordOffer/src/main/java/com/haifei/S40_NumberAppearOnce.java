package com.haifei;

/**
 * Create by haifei on 13/11/2018 11:59 PM.
 * 数组中仅有两个出现一次的数字，要求时间复杂度为o（n）空间复杂度为o（1）：异或做法
 */
public class S40_NumberAppearOnce {

  void findNumsAppearOnce(int[] data) {
    int num1 = 0;
    int num2 = 0;

    int resultExclusivOR = 0;
    for (Integer i : data) {
      resultExclusivOR ^= i;
    }

    int index = findFirstBits1(resultExclusivOR);

    for (int i = 0; i < data.length; i++) {
      if (isBit1(data[i], index)) {
        num1 ^= data[i];
      } else {
        num2 ^= data[i];
      }
    }
  }

  public int findFirstBits1(int num) {
    int indexBit = 0;
    while ((num & 1) == 0) {
      num = num >> 1;
      indexBit++;
    }

    return indexBit;
  }

  public boolean isBit1(int num, int index) {
    num = num >> index;
    return (num & 1) != 0;
  }
}
