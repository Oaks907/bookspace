package list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by haifei on 6/11/2018 2:50 PM.
 */
public class StreamListTest {

  public static void main(String[] args) {
    Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
    List<Integer> collectList  = Arrays.asList(nums);


    int page = 2;
    int limit = 2;

    final List<Integer> collect = collectList.stream()
      .skip(page * limit)
      .limit(limit)
      .collect(Collectors.toList());

    System.out.println(collect);

    System.out.println(collectList.hashCode());
  }
}
