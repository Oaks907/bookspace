package com.haifei;

/**
 * Create by haifei on 3/8/2018.
 */
public class S24_VerifySequenceOfBST {

  private boolean verifySequenceOfBST(int[] sequence) {

    return verifySequenceOfBSTRecursion(sequence, 0, sequence.length - 1);
  }

  private boolean verifySequenceOfBSTRecursion(int[] sequence, int start, int end) {
    if (sequence == null || sequence.length < 2) {
      return true;
    }

    int rootValue = sequence[end];
    int i = start;
    for (; i < end; i++) {
      if (sequence[i] > rootValue) {
        break;
      }
    }

    int j = i;
    for (; j < end; j++) {
      if (sequence[j] < rootValue) {
        return false;
      }
    }


    boolean verifyLeft = true;
    if (start < i) {
      verifyLeft = verifySequenceOfBSTRecursion(sequence, start, i - 1);
    }
    boolean verifyRight = true;
    if (i < end) {
      verifyRight = verifySequenceOfBSTRecursion(sequence, i, end - 1);
    }

    return (verifyLeft && verifyRight);
  }

  public static void main(String[] args) throws Exception {
    int[] numbers = {5, 7, 6, 9, 11, 8, 10};
    S24_VerifySequenceOfBST verifySequenceOfBST = new S24_VerifySequenceOfBST();
    System.out.println(verifySequenceOfBST.verifySequenceOfBST(numbers));
    numbers = new int[]{5, 7, 6, 9, 11, 10, 8};
    System.out.println(verifySequenceOfBST.verifySequenceOfBST(numbers));
  }


}
