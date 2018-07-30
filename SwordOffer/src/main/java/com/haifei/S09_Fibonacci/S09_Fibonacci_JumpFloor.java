package com.haifei.S09_Fibonacci;

/**
 * Create by haifei on 30/7/2018.
 */
public class S09_Fibonacci_JumpFloor {

  public long jumpFloor_normal(int n) {
    if (n == 0) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }
    if (n == 2) {
      return 2;
    }
    return jumpFloor_normal(n - 1) + jumpFloor_normal(n - 2);
  }

  public long jumpFloor_better(int n) {
    int result[] = {0, 1, 2};
    if (n <= 2) {
      return result[n];
    }
    long firstValue = 1;
    long twoValue = 2;

    long jumpValue = 0;

    for (int i = 3; i <= n; i++) {
      jumpValue = firstValue + twoValue;
      firstValue = twoValue;
      twoValue = jumpValue;
    }

    return jumpValue;
  }

  public static void main(String[] args) {
    int[] valueArr = {0, 1, 2, 3, 4, 5, 6, 7};

    long currentTime = System.currentTimeMillis();
    S09_Fibonacci_JumpFloor fibonacci = new S09_Fibonacci_JumpFloor();
    for (int i : valueArr) {
      System.out.println(fibonacci.jumpFloor_normal(i));
    }
    System.out.println("递归耗用的时间：" + (System.currentTimeMillis() - currentTime));
    currentTime = System.currentTimeMillis();

    for (int i : valueArr) {
      System.out.println(fibonacci.jumpFloor_better(i));
    }

    System.out.println("循环耗用的时间：" + (System.currentTimeMillis() - currentTime));
  }
}
