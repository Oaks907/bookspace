package S1001_S1100;

/**
 * Create by haifei on 19/3/2019 7:38 PM.
 * https://leetcode.com/problems/complement-of-base-10-integer/
 */
public class S1012_ComplementofBase10Integer {

  public int bitwiseComplement(int N) {
    int n = 1;

    while (N > n) {
      n = 2 * n + 1;
    }

    return N ^ n;
  }

  public static void main(String[] args) {

    final S1012_ComplementofBase10Integer base10Integer =
      new S1012_ComplementofBase10Integer();

    System.out.println(base10Integer.bitwiseComplement(5)); //2
    System.out.println(base10Integer.bitwiseComplement(7)); //0
    System.out.println(base10Integer.bitwiseComplement(10)); //5
  }
}
