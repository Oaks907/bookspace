package S201_300;

/**
 * Create by haifei on 20/1/2019 7:58 PM.
 */
public class S231_PowerofTwo {

  public boolean isPowerOfTwo(int n) {

    return n > 0 && ((n & (n-1)) == 0);
  }

  public static void main(String[] args) {

  }
}
