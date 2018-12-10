package S101_200;

import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create by haifei on 10/12/2018 7:55 PM.
 * https://leetcode.com/problems/triangle/
 */
public class S120_Triangle {

  public int minimumTotal(List<List<Integer>> triangle) {

    if (triangle == null || triangle.size() == 0) {
      return 0;
    }

    int[] dp = new int[triangle.get(triangle.size() - 1).size() + 1];
    for (int i = triangle.size() - 1; i >= 0; i--) {
      final List<Integer> list = triangle.get(i);
      for (int j = 0; j <= i; j++) {

        dp[j] = Math.min(dp[j], dp[j + 1]) + list.get(j);
      }
    }

    return dp[0];
  }

  public static void main(String[] args) {
    List<Integer> list1 = Arrays.asList(2);
    List<Integer> list2 = Arrays.asList(3, 4);
    List<Integer> list3 = Arrays.asList(6, 5, 7);
    List<Integer> list4 = Arrays.asList(4, 1, 8, 3);

    List<List<Integer>> lists = new ArrayList<>();
    lists.add(list1);
    lists.add(list2);
    lists.add(list3);
    lists.add(list4);

    final S120_Triangle triangle = new S120_Triangle();
    System.out.println(triangle.minimumTotal(lists));

    list1 = Arrays.asList(-1);
    list2 = Arrays.asList(2, 3);
    list3 = Arrays.asList(1, -1, -3);
    lists.clear();
    lists.add(list1);
    lists.add(list2);
    lists.add(list3);
    System.out.println(triangle.minimumTotal(lists));
  }

}
