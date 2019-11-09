package contest.contest_160;

import org.junit.Test;
import utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 27/10/2019 10:45 AM.
 */
public class CircularPermutationinBinaryRepresentation {

  List<Integer> result = new ArrayList<>();
  List<String> list = new ArrayList<>();

  public List<Integer> circularPermutation(int n, int start) {


    return result;
  }

  private void helper(String[] str, int index) {
    if (index == str.length) {
      System.out.print(strToNumber(str) + " ==> ");
      PrintUtils.printArray(str);
      return;
    }

    for (int i = index; i < str.length; i++) {
      str[i] = "1";
      helper(str, i + 1);
      str[i] = "0";
    }
  }

  private int strToNumber(String[] split) {

    int result = 0;

    for (int i = split.length - 1; i >= 0; i--) {
      result += Integer.parseInt(split[i]) == 1 ? Math.pow(2, split.length - i - 1) : 0;
    }

    return result;
  }

  @Test
  public void test() {

    String[] strs = {"0", "0", "0"};

    helper(strs, 0);

    System.out.println(list);
  }
}
