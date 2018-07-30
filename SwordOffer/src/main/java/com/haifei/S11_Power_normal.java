package com.haifei;

/**
 * Create by haifei on 30/7/2018.
 */
public class S11_Power_normal {

  private double power(double base, int exponent) {
    if (base < 0) {
      throw new IllegalArgumentException();
    }
    boolean isNegative = false;
    if (exponent < 0) {
      exponent = -exponent;
      isNegative = true;
    }

    double result = powerWithExponent(base, exponent);


    if (isNegative) {
      result = 1 / result;
    }

    return result;
  }

  private double powerWithExponent(double base, int x) {
    if (x == 0) {
      return 1;
    }
    if (x == 1) {
      return base;
    }

    double result = powerWithExponent(base, x >> 1);
    result *= result;

    if ((x & 1) ==1) {
      result *= base;
    }

    return result;
  }

  public static void main(String[] args) {
    S11_Power_normal powerNormal = new S11_Power_normal();
    System.out.println(powerNormal.power(2.00000, 10));//1024.00000
    System.out.println(powerNormal.power(2.10000, 3));//9.26100
    System.out.println(powerNormal.power(2.00000, -2));//0.25000
  }

}
