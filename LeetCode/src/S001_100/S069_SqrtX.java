package S001_100;

/**
 * Create by haifei on 19/1/2019 3:13 PM.
 * <p>
 * https://leetcode.com/problems/sqrtx/
 */
public class S069_SqrtX {

  public int mySqrt(int x) {
    if (x == 0 || x == 1) {
      return x;
    }

    int left = 0;
    int right = x;

    while (left < right - 1) {
      int mid = left + (right - left) / 2;
      if (mid == x / mid) {
        return mid;
      } else if (mid < x / mid) {
        left = mid;
      } else {
        right = mid;
      }
    }

    return left;
  }

  public static void main(String[] args) {
    final S069_SqrtX sqrtX = new S069_SqrtX();
    System.out.println(sqrtX.mySqrt(2));
    System.out.println(sqrtX.mySqrt(3));
    System.out.println(sqrtX.mySqrt(8)); // 2

  }
}
