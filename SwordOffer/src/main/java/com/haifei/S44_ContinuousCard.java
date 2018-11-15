package com.haifei;

import java.util.Arrays;

/**
 * Create by haifei on 15/11/2018 11:54 PM.
 */
public class S44_ContinuousCard {

  public boolean isContinuous(int[] numbers) {
    if (null == numbers || numbers.length < 1) {
      return false;
    }

    Arrays.sort(numbers);

    int numberOfGap = 0;
    int numberOfZero = 0;

    for (int i = 0; i < numbers.length && numbers[i] == 0; i++) {
      numberOfZero++;
    }

    int small = numberOfZero;
    int big = small + 1;

    while (big < numbers.length) {

      if (numbers[small] == numbers[big]) {
        return false;
      }
      numberOfGap = numbers[big] - numbers[small] - 1;
      small = big;
      big++;
    }

    return numberOfGap <= numberOfZero;
  }
}
