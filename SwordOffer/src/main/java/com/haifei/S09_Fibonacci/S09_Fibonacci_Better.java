package com.haifei.S09_Fibonacci;


/**
 * Create by haifei on 30/7/2018.
 */
public class S09_Fibonacci_Better {

  private long fibonacci(int n) {
    long result = 0;
    long baseArr[] = {0, 1};
    if (n < 2) {
      return baseArr[n];
    }
    long fiboOne = baseArr[0];
    long fiboTwo = baseArr[1];

    for (int i = 2; i <= n; i++) {
      result = fiboOne + fiboTwo;
      fiboOne = fiboTwo;
      fiboTwo = result;
    }

    return result;
  }

  public static void main(String[] args) {
    int[] valueArr = {0, 1 , 2, 3, 4, 5, 6, 7};
    S09_Fibonacci_Better fibonacci = new S09_Fibonacci_Better();
    for (int i : valueArr) {
      System.out.println(fibonacci.fibonacci(i));
    }
  }
}
