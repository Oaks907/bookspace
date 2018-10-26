package com.haifei;

/**
 * Create by haifei on 26/10/2018 12:59 PM.
 */
public class S34_getUglyNumber {
  public static void main(String[] args) {
    System.out.println(getUglyNumber(1500));
    System.out.println(getUglyNumber(2));
  }

  private static int getUglyNumber(int index) {
    if (index <= 0) {
      return 0;
    }
    if (index == 1) {
      return 1;
    }
    int[] numbers = new int[index];
    numbers[0] = 1;
    int t2 = 0;
    int t3 = 0;
    int t5 = 0;

    for (int i = 1; i < numbers.length; i++) {
      numbers[i] = Math.min(numbers[t2] * 2, Math.min(numbers[t3] * 3, numbers[t5] * 5));
      if (numbers[i] == numbers[t2] * 2) {
        t2++;
      }
      if (numbers[i] == numbers[t3] * 3) {
        t3++;
      }
      if (numbers[i] == numbers[t5] * 5) {
        t5++;
      }
    }

    return numbers[index - 1];
  }
}
