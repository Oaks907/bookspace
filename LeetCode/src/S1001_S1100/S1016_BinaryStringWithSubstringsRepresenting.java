package S1001_S1100;

import org.junit.Test;

/**
 * Create by haifei on 24/3/2019 9:47 AM.
 */
public class S1016_BinaryStringWithSubstringsRepresenting {

  public boolean queryString(String S, int N) {

    for(int i = 0; i <= N; i++) {
      if (!S.contains(Integer.toBinaryString(i))){
        return false;
      }
    }

    return true;
  }

  @Test
  public void test() {
    final S1016_BinaryStringWithSubstringsRepresenting representing = new S1016_BinaryStringWithSubstringsRepresenting();

    System.out.println(representing.queryString("110101011011000011011111000000", 15));
  }


}
