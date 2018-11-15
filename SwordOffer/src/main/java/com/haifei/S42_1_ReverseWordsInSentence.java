package com.haifei;

/**
 * Create by haifei on 14/11/2018 1:10 PM.
 */
public class S42_1_ReverseWordsInSentence {

  public String reverse(String str) {
    String[] strings = str.split("");
    int left = 0, right = strings.length - 1;

    while (left < right) {
      String temp = strings[left];
      strings[left] = strings[right];
      strings[right] = temp;
      left++;
      right--;
    }

    return String.valueOf(strings);
  }

  public String reverseString(String str) {
    String[] strArr = str.split(" ");
    int left = 0, right = strArr.length - 1;

    while (left < right) {
      String temp = strArr[left];
      strArr[left] = strArr[right];
      strArr[right] = temp;
      left++;
      right--;
    }

    String result = strArr[0];
    for (int i = 1; i < strArr.length; i++) {
      result += " " + strArr[i];
    }

    System.out.println(result);
    return result;
  }

  public static void main(String[] args) {
    new S42_1_ReverseWordsInSentence().reverseString("I am a student.");
  }

}
