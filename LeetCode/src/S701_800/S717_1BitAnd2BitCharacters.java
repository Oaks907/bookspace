package S701_800;

/**
 * Create by haifei on 5/1/2018.
 */
public class S717_1BitAnd2BitCharacters {


  public static void main(String[] args) {
    int nums1[] = {1, 0, 1, 0, 1, 0};
    System.out.println(arrayPairSum1(nums1));
  }

  public static boolean isOneBitCharacter(int[] bits) {
    int n = bits.length;
    int i = 0;
    while (i < n - 1) {
      if (bits[i] == 0) {
        i++;
      } else {
        i += 2;
      }
    }
    return n - 1== i;
  }

  private static boolean arrayPairSum1(int[] bits) {
    int ones = 0;
    for (int i = bits.length - 2; i >= 0 && bits[i] != 0; i--) {
      ones++;
    }
    if (ones % 2 > 0) return false;
    return true;
  }
}
