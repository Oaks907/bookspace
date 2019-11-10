package S1201_S1300;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Create by haifei on 9/11/2019 11:32 PM.
 */
public class S1249_MinimumRemovetoMakeValidParentheses {

  public String minRemoveToMakeValid(String s) {

    Stack<Integer> stack = new Stack<>();
    List<Integer> delIndexList = new ArrayList<>();

    String[] split = s.split("");
    for (int i = 0; i < split.length; i++) {
      if (split[i].equals(")")) {
        if (stack.isEmpty()) {
          delIndexList.add(i);
        } else {
          stack.pop();
        }
      } else if ("(".equals(split[i])) {
        stack.add(i);
      }
    }

    delIndexList.addAll(stack);

    StringBuilder result = new StringBuilder();

    for (int i = 0; i < split.length; i++) {
      if (!delIndexList.contains(i)) {
        result.append(split[i]);
      }
    }

    return result.toString();
  }

  @Test
  public void test() {
    String s = "lee(t(c)o)de)";

    String result = minRemoveToMakeValid(s);

    System.out.println(result);
  }

  @Test
  public void test1() {
    String s = "a)b(c)d";

    String result = minRemoveToMakeValid(s);

    System.out.println(result);
  }

  @Test
  public void test2() {
    String s = "))((";

    String result = minRemoveToMakeValid(s);

    System.out.println(result);
  }

  @Test
  public void test3() {
    String s = "(a(b(c)d)";

    String result = minRemoveToMakeValid(s);

    System.out.println(result);
  }

}
