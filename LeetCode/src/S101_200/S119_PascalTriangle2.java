package S101_200;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 3/2/2018.
 */
public class S119_PascalTriangle2 {

  public static List<Integer> getRow(int rowIndex) {
    List<List<Integer>> result = new ArrayList<>();

    for (int i = 0; i <= rowIndex; i++) {
      List<Integer> arrayList = new ArrayList<>();
      for (int j = 0; j <= i; j++) {
        if (j == 0 || j == i)  {
          arrayList.add(1);
        } else {
          arrayList.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
        }
      }
      result.add(arrayList);
    }

    return result.get(rowIndex );
  }

  public static void main(String[] args) {
    System.out.println(getRow(3));
  }
}
