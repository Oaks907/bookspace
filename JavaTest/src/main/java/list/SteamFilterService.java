package list;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by haifei on 19/11/2018 3:50 PM.
 */
public class SteamFilterService {

  public static void main(String[] args) {
    final List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6);

    final List<Integer> collect =
      integerList.stream().filter(o -> o == 2).collect(Collectors.toList());

    System.out.println(collect);
  }
}
