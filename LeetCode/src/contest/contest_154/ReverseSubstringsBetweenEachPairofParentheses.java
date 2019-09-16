package contest.contest_154;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Create by haifei on 15/9/2019 10:44 AM.
 */
public class ReverseSubstringsBetweenEachPairofParentheses {
  public String reverseParentheses(String s) {
    if (s == null || s.isEmpty()) {
      return s;
    }
    String[] split = s.split("");
    Stack<String> stack = new Stack<>();

    for (int i = 0; i < split.length; i++) {
      if (")".equals(split[i])) {
        StringBuilder stringBuilder = getStrFromABracket(stack);
        String[] reverserSplit = stringBuilder.toString().split("");
        for (String item : reverserSplit) {
          stack.push(item);
        }
      } else {
        stack.push(split[i]);
      }
    }

    return getStrFromABracket(stack).reverse().toString();
  }

  private StringBuilder getStrFromABracket(Stack<String> stack) {
    StringBuilder stringBuilder = new StringBuilder();
    while (!stack.isEmpty()) {
      String pop = stack.pop();
      if (pop.equals("(")) {
        break;
      }
      stringBuilder.append(pop);
    }

    return stringBuilder;
  }

  @Test
  public void test() {
    ReverseSubstringsBetweenEachPairofParentheses reverse =
      new ReverseSubstringsBetweenEachPairofParentheses();

    String result = reverse.reverseParentheses("(abcd)");
    System.out.println(result);

    Assert.assertEquals("dcba", result);
  }

  @Test
  public void test1() {
    ReverseSubstringsBetweenEachPairofParentheses reverse =
      new ReverseSubstringsBetweenEachPairofParentheses();

    String result = reverse.reverseParentheses("(u(love)i)");
    System.out.println(result);

    Assert.assertEquals("iloveu", result);
  }

  @Test
  public void test2() {
    ReverseSubstringsBetweenEachPairofParentheses reverse =
      new ReverseSubstringsBetweenEachPairofParentheses();

    String result = reverse.reverseParentheses("(ed(et(oc))el)");
    System.out.println(result);

    Assert.assertEquals("leetcode", result);
  }

  @Test
  public void test3() {
    ReverseSubstringsBetweenEachPairofParentheses reverse =
      new ReverseSubstringsBetweenEachPairofParentheses();

    String result = reverse.reverseParentheses("a(bcdefghijkl(mno)p)q");
    System.out.println(result);

    Assert.assertEquals("apmnolkjihgfedcbq", result);
  }
}
