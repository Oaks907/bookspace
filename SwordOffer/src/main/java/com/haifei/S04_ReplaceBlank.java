package com.haifei;

/**
 * Create by haifei on 24/6/2018.
 */
public class S04_ReplaceBlank {

  private static String replaceBlank(String string) {
    int length = string.length();
    char[] charArr = string.toCharArray();
    int blankSize = 0;
    for (char val : charArr) {
      if (val == ' ') {
        blankSize++;
      }
    }
    char[] result = new char[length + 2 * blankSize];
    int resultLength = result.length - 1;

    for (int i = charArr.length - 1; i >= 0; i--) {
      if (charArr[i] != ' ') {
        result[resultLength--] = charArr[i];
      } else {
        result[resultLength--] = '0';
        result[resultLength--] = '2';
        result[resultLength--] = '%';
      }
    }
    StringBuilder stringBuilder = new StringBuilder();
    for (char val : result) {
      stringBuilder.append(val);
    }
    string = stringBuilder.toString();
    return string;
  }

  public static void main(String[] args) {
    System.out.println(replaceBlank("We Are Happy"));
  }
}
