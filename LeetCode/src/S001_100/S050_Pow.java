package S001_100;

/**
 * Create by haifei on 13/1/2019 10:32 PM.
 */
public class S050_Pow {

  public double myPow(double x, int n) {
    if (n < 0) {
      x = 1 / x;
      n = -n;
    }

    double result = 1;
    while (n > 0) {
      if (n % 2 == 1) {
        result *= x;
      }
      x = x * x;
      n = n / 2;
    }
    return result;
  }

  public static void main(String[] args) {

  }
}
