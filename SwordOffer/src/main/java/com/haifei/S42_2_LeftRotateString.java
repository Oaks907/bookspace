package com.haifei;

import java.util.Arrays;

/**
 * Create by haifei on 14/11/2018 2:28 PM.
 */
public class S42_2_LeftRotateString {

  public String leftRotateString(String str, int n) {
    if (null == str ) {
      return str;
    }

    str = reverse(str, 0, n - 1);
    System.out.println(str);
    str = reverse(str, n, str.length() - 1);
    System.out.println(str);
    str = reverse(str, 0, str.length() - 1);
    System.out.println(str);
    return  str;
  }

  private String reverse(String str, int start, int end) {
    char[] strs = str.toCharArray();

    while (start < end) {
      char temp = strs[start];
      strs[start] = strs[end];
      strs[end] = temp;
      start++;
      end--;
    }

    return String.valueOf(strs);
  }

  public static void main(String[] args) {
    new S42_2_LeftRotateString().leftRotateString("abcdefg", 2);
  }

}
