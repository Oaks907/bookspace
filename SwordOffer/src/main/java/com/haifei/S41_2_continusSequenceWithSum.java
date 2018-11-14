package com.haifei;

/**
 * Create by haifei on 14/11/2018 8:20 AM.
 */
public class S41_2_continusSequenceWithSum {

  void findContinuousSequence(int sum) {
    if (sum < 3) {
      return;
    }
    int small = 1;
    int big = 2;
    int middle = (sum + 1) / 2;
    int curSum = small + big;

    //small必须小于sum值的一半，才有可能加上后面的一个值等于sum。
    while (small < middle) {
      if (curSum == sum) {
        printContinuousSequence(small, big);
      }

      while (curSum > sum && small < middle) {
        curSum -= small;
        small++;
        if (curSum == sum) {
          printContinuousSequence(small, big);
        }
      }

      big++;
      curSum += big;
    }
  }

  private void printContinuousSequence(int small, int big) {
    for (int i = small; i <= big; ++i) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    new S41_2_continusSequenceWithSum().findContinuousSequence(15);
  }
}
