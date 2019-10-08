package S801_S900;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 7/10/2019 6:18 PM.
 */
public class S842_SplitArrayIntoFibonacciSequence {

  public List<Integer> splitIntoFibonacci(String S) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    partition(S, result, temp);

    return result.size() == 0 ? new ArrayList<>() : result.get(0);
  }

  private void partition(String S, List<List<Integer>> result, List<Integer> temp) {

    if (S.isEmpty() && temp.size() >= 3) {
      result.add(new ArrayList<>(temp));
      return;
    }

    for (int i = 1; i <= S.length(); i++) {
      String sec = S.substring(0, i);
      long check_value_sec = Long.parseLong(sec);

      if (sec.length() > 1 && sec.charAt(0) == '0') break;
      if (check_value_sec > Integer.MAX_VALUE || check_value_sec < 0)
        break; // check whether the number is too large here(>Integer.MAX_VALUE)

      int value_sec = Integer.parseInt(sec);
      temp.add(value_sec);

      if (temp.size() <= 2 || (temp.get(temp.size() - 3) + temp.get(temp.size() - 2) == value_sec))
        partition(S.substring(i), result, temp);

      temp.remove(temp.size() - 1);

    }
  }
}
