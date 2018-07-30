package com.haifei;

/**
 * Create by haifei on 30/7/2018.
 */
public class S11_Power_better {

  public double power(double x, int n) {
    long N = n;
    if (N < 0){
      x = 1 / x;
      N = -N;
    }
    double res = 1;
    while (N > 0){
      if (N % 2 == 1){
        res = res * x;
      }
      N = N / 2;
      x = x * x;
    }
    return res;
  }

  public static void main(String[] args) {
    S11_Power_better powerNormal = new S11_Power_better();
    System.out.println(powerNormal.power(2.00000, 10));//1024.00000
    System.out.println(powerNormal.power(2.10000, 3));//9.26100
    System.out.println(powerNormal.power(2.00000, -2));//0.25000
  }


}
