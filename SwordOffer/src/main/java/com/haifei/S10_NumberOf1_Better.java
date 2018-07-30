package com.haifei;

/**
 * Create by haifei on 30/7/2018.
 */
public class S10_NumberOf1_Better {

  private int numberOf1(int n) {
    int result = 0;
    while (n > 0) {
      result++;
      n = (n & n - 1);
    }
    return result;
  }

  public static void main(String[] args) {
    int valueArr[] = {1, 2, 3, 4, 5, 6, 7, 100};

    S10_NumberOf1_Better numberOf1_better = new S10_NumberOf1_Better();
    for (int i : valueArr) {
      System.out.println(numberOf1_better.numberOf1(i));
    }
  }
}
