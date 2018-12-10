package S401_500;

/**
 * Create by haifei on 9/12/2018 11:38 AM.
 * https://leetcode.com/problems/arithmetic-slices/
 */
public class S413_ArithmeticSlices {

  //关键是找出状态转移方程
  //(n-1)(n-2)/2
  public int numberOfArithmeticSlices(int[] A) {
    int res = 0;
    int len = 2;
    int n = A.length;

    for (int i = 2; i < n; i++) {
      if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
        len++;
      } else {
        if (len > 2) {
          res += (len - 1) * (len - 2) / 2;
        }
        len = 2;
      }
    }
    if (len > 2) res += (len - 1) * (len - 2) * 0.5;
    return  res;
  }

  public static void main(String[] args) {
    final S413_ArithmeticSlices arithmeticSlices = new S413_ArithmeticSlices();

    int[] A = {1, 3, 5, 7, 9};

    System.out.println(arithmeticSlices.numberOfArithmeticSlices(A));

    A = new int[]{1, 1, 3, 5, 7};

    System.out.println(arithmeticSlices.numberOfArithmeticSlices(A));
  }
}
