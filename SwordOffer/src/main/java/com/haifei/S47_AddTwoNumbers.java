package com.haifei;

/**
 * Create by haifei on 16/11/2018 8:19 AM.
 */
public class S47_AddTwoNumbers {

  public int add(int num1, int num2) {

    int carry, sum;

    do {
      sum = num1 ^ num2;
      carry = (num1 & num2) << 1;

      num1 = sum;
      num2 = carry;
    } while (num2 != 0);

    return num1;
  }

  public static void main(String[] args) {
    System.out.println(new S47_AddTwoNumbers().add(5, 6));
  }
}
