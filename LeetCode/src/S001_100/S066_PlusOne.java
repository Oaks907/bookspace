package S001_100;

/**
 * Create by haifei on 29/1/2018.
 */
public class S066_PlusOne {
  public static int[] plusOne(int[] digits) {
    boolean isAllNine = true;
    for (int i : digits) {
      if (i != 9) {
        isAllNine = false;
        break;
      }
    }
    int[] result;
    if (isAllNine) {
      result = new int[digits.length + 1];
      result[0] = 1;
      return result;
    }
    int upper = 0;
    digits[digits.length - 1] += 1;
    for (int i = digits.length - 1; i >= 0; i--) {
      int value = digits[i] + upper;
      if (value >= 10) {
        digits[i] = value - 10;
        upper = 1;
      } else {
        digits[i] = value;
        break;
      }
    }

    for (int i : digits) {
      System.out.print(i);
    }
    return digits;
  }

  public static void main(String[] args) {
    int[] nums1 = {8, 9, 9, 9};
    plusOne(nums1);
  }
}
