package contest.contest_152;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create by haifei on 1/9/2019 10:37 AM.
 */
public class S1177CanMakePalindromefromSubstring {


  public List<Boolean> canMakePaliQueries(String s, int[][] queries) {

    List<Boolean> result = new ArrayList<>();

    for (int i = 0; i < queries.length; i++) {
      int left = queries[i][0];
      int right = queries[i][1];
      int canChange = queries[i][2];
      result.add(canMakePal(s, left, right, canChange));
    }

    return result;
  }

  private boolean canMakePal(String s, int start, int end, int max) {
    if (max > 13) {
      return true;
    }
    Set<Character> set = new HashSet();
    for (int i = start; i <= end; i++) {
      if (!set.add(s.charAt(i))) {
        set.remove(s.charAt(i));
      }
    }
    return max >= set.size() / 2;
  }

  @Test
  public void test() {
    S1177CanMakePalindromefromSubstring palindromefromSubstring =
      new S1177CanMakePalindromefromSubstring();

    int[][] nums = {{3, 3, 0}, {1, 2, 0}, {0, 3, 1}, {0, 3, 2}, {0, 4, 1}};
    System.out.println(palindromefromSubstring.canMakePaliQueries("abcda", nums));
  }

  @Test
  public void test1() {

    S1177CanMakePalindromefromSubstring palindromefromSubstring =
      new S1177CanMakePalindromefromSubstring();

    int[][] nums = {{0, 3, 1}, {1, 1, 1}};

    System.out.println(palindromefromSubstring.canMakePaliQueries("hunu", nums));
  }
}

