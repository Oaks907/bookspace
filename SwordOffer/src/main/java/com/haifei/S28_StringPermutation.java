package com.haifei;

/**
 * Create by haifei on 8/8/2018.
 */
public class S28_StringPermutation {

  private void permutation(String str) {
    if (str == null) {
      return;
    }
    permutation(str, 0);
  }

  private void permutation(String str, int begin) {
    if (begin == str.length()) {
      System.out.println(str);
    } else {
      for (int i = begin; i < str.length(); i++) {
        char[] chars = str.toCharArray();
        char temp = chars[begin];
        chars[begin] = chars[i];
        chars[i] = temp;

        str = new String(chars);

        permutation(str, begin + 1);

        chars = str.toCharArray();
        temp = chars[begin];
        chars[begin] = chars[i];
        chars[i] = temp;

        str = new String(chars);
      }
    }
  }

  public static void main(String[] args) {
    new S28_StringPermutation().permutation("abc");
  }
}
