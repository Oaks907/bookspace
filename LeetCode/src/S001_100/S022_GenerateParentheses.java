package S001_100;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 15/1/2019 11:07 PM.
 * <p>
 * https://leetcode.com/problems/generate-parentheses/
 */
public class S022_GenerateParentheses {

  public List<String> generateParenthesis(int n) {

    List<String> list = new ArrayList<>();

    if (n == 0) {
      return list;
    }

    recursion(list, n, n, "");
    return list;
  }

  private void recursion(List<String> list, int left, int right, String str) {
    if (left < 0 || right < 0) {
      return;
    }
    if (left == 0 && right == 0) {
      list.add(str);
      return;
    }

    if (left < right) {
      recursion(list, left, right - 1, str + ")");
    }
    recursion(list, left - 1, right, str + "(");
  }

  public static void main(String[] args) {

    final S022_GenerateParentheses generateParentheses = new S022_GenerateParentheses();
    System.out.println(generateParentheses.generateParenthesis(3));
  }
}
