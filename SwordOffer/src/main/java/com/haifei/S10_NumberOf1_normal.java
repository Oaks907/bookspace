package com.haifei;

/**
 * Create by haifei on 30/7/2018.
 */
public class S10_NumberOf1_normal {
  private int numberOf1(int n) {
    int result = 0;
    int flag = 1;
    while (flag > 0 && flag <= n) {
      if ((n & flag) > 0) {
        result++;
      }
      flag = flag << 1;
    }
    return result;
  }

  public static void main(String[] args) {
    int valueArr[] = {1, 2, 3, 4, 5, 6, 7, 100};

    S10_NumberOf1_normal s10_numberOf1_normal = new S10_NumberOf1_normal();
    for (int i : valueArr) {
      System.out.println(s10_numberOf1_normal.numberOf1(i));
    }
  }
}
