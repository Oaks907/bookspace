package S1001_S1100;

import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

/**
 * Create by haifei on 7/4/2019 11:10 AM.
 */
public class S0_RemoveOutermostParentheses {

  public String removeOuterParentheses(String S) {
    if (S == null || S.length() == 0) {
      return S;
    }

    final String[] split = S.split("");
    Stack<String> stack = new Stack<>();
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < split.length; i++) {

      String str = split[i];

      if (stack.isEmpty()) {
        stack.push(str);
        result.append(str);
        continue;
      }

      final String peek = stack.peek();

      if (peek.equals("(") && str.equals(")")) {
        stack.pop();
        if (stack.isEmpty()) {
          result = result.deleteCharAt(0);
        } else {
          result.append(str);
        }
       } else {
        stack.push(str);
        result.append(str);
      }
    }

    return result.toString();
  }

  S0_RemoveOutermostParentheses parentheses ;

  @Before
  public void before() {
    parentheses  = new S0_RemoveOutermostParentheses();
  }

  @Test
  public void test1() {
    System.out.println(parentheses.removeOuterParentheses("(()())(())"));
  }
}
