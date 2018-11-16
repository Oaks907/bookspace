package com.haifei;

/**
 * Create by haifei on 16/11/2018 12:24 AM.
 */
public class S45_2_LastNumberInCircle {

  public int lastRemaining(int n, int m) {
    if (n < 1 || m < 1) {
      return -1;
    }
    int last = 0;

    for (int i = 2; i <= n; i++) {
      last = (last + m) % i;
    }

    return last;
  }

  public static void main(String[] args) {
    System.out.println(new S45_2_LastNumberInCircle().lastRemaining(5, 3));
  }
}
