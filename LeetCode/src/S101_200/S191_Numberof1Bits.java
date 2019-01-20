package S101_200;

/**
 * Create by haifei on 20/1/2019 5:39 PM.
 */
public class S191_Numberof1Bits {

  // you need to treat n as an unsigned value
  public int hammingWeight(int n) {

    int count = 0;
    while (n != 0) {

      count++;

      n = n & (n - 1);
    }

    return count;
  }

  public static void main(String[] args) {
    final S191_Numberof1Bits numberof1Bits = new S191_Numberof1Bits();

    System.out.println(numberof1Bits.hammingWeight(8));
    System.out.println(numberof1Bits.hammingWeight(7));
    System.out.println(numberof1Bits.hammingWeight(6));

  }
}
