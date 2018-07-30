package com.haifei.S09_Fibonacci;

/**
 * Create by haifei on 30/7/2018.
 */
public class S09_Fibonacci_normal {

  private long fibonacci(int n) {
    if (n == 0) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }

    return fibonacci(n - 1) + fibonacci(n - 2);
  }

  public static void main(String[] args) {
    int[] valueArr = {0, 1 , 2, 3, 4, 5, 6, 7};
    S09_Fibonacci_normal fibonacci = new S09_Fibonacci_normal();
    for (int i : valueArr) {
      System.out.println(fibonacci.fibonacci(i));
    }
  }
}
