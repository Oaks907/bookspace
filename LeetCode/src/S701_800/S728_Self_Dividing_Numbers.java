package S701_800;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 16/1/2018.
 */
public class S728_Self_Dividing_Numbers {
  public static List<Integer> selfDividingNumbers(int left, int right) {
    List<Integer> result = new ArrayList<>();
    for (int i = left; i <= right; i++) {
      int value = i;
      while (value > 0) {
        int temp = value % 10;
        if (temp != 0 && i % temp == 0) {
          value = (value - temp) / 10;
        } else {
          break;
        }
      }
      if (value == 0) {
        result.add(i);
      }
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(selfDividingNumbers(1, 22));
  }
}
