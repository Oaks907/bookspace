package com.haifei;

/**
 * Create by haifei on 30/7/2018.
 */
public class S12_Print1ToMaxOfAnDigit {

  private void print1ToMaxOfNDigits(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException();
    }

    String printStr[] = new String[n];
    for (int i = 0; i < printStr.length; i++) {
      printStr[i] = "0";
    }

    while (!increment(printStr)) {
      printNumber(printStr);
    }
  }

  private boolean increment(String str[]) {
    boolean isOverflow = false;
    int nTakeOver = 0;
    for (int i = str.length - 1; i >= 0; i--) {
      int currentNumber = Integer.parseInt(str[i]) + nTakeOver;
      if (i == str.length - 1) {
        currentNumber++;
      }

      if (currentNumber <= 9) {
        str[i] = String.valueOf(currentNumber);
        isOverflow = false;
        break;
      } else if (i == 0 && currentNumber > 9) {
        isOverflow = true;
      } else if (currentNumber > 9) {
        int temp = currentNumber % 10;
        nTakeOver = (currentNumber - temp) / 10;
        currentNumber = temp;
        str[i] = String.valueOf(currentNumber);
        isOverflow = false;
      }
    }

    return isOverflow;
  }

  private void printNumber(String str[]) {
    boolean isBegin = true;
    for (int i = 0; i < str.length; i++) {
      if (isBegin && !str[i].equals("0")) {
        isBegin = false;
      }

      if (!isBegin) {
        System.out.print(str[i]);
      }
    }
    System.out.println();
  }

  public static void main(String[] args) {
    S12_Print1ToMaxOfAnDigit print1ToMaxOfAnDigit = new S12_Print1ToMaxOfAnDigit();
    print1ToMaxOfAnDigit.print1ToMaxOfNDigits(3);
  }
}
