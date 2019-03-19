package S901_S1000;

/**
 * Create by haifei on 27/1/2019 10:33 AM.
 */
public class S984_StringWithoutAAAOrBBB {

  public String strWithout3a3b(int A, int B) {
    StringBuilder sb = new StringBuilder();

    int length = A + B;

    for (int i = 0; i < length; i++) {
      if (A > B) {
        if (i >= 2 && sb.charAt(i - 1) == 'a' && sb.charAt(i - 2) == 'a') {
          sb.append('b');
          B--;
        } else {
          sb.append('a');
          A--;
        }
      } else {
        if (i >= 2 && sb.charAt(i - 1) == 'b' && sb.charAt(i - 2) == 'b') {
          sb.append('a');
          A--;
        } else {
          sb.append('b');
          B--;
        }
      }
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    final S984_StringWithoutAAAOrBBB aaaOrBBB = new S984_StringWithoutAAAOrBBB();
    System.out.println(aaaOrBBB.strWithout3a3b(4, 1));
    System.out.println(aaaOrBBB.strWithout3a3b(1, 4));
    System.out.println(aaaOrBBB.strWithout3a3b(4, 4));//"abababab"
    System.out.println(aaaOrBBB.strWithout3a3b(2, 6));
  }
}
