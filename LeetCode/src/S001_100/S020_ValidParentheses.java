package S001_100;

import java.util.Stack;

/**
 * Create by haifei on 12/1/2019 5:17 PM.
 * <p>
 * Input: "()[]{}"
 * Output: true
 * <p>
 * https://leetcode.com/problems/valid-parentheses/
 */
public class S020_ValidParentheses {


  public boolean isValid(String s) {

    if (null == s || s.isEmpty()) {
      return true;
    }

    final String[] split = s.split("");
    Stack<String> stack = new Stack<>();

    for (int i = 0; i < split.length; i++) {

      final String s1 = split[i];

      if (s1.equals("(") || s1.equals("[") || s1.equals("{")) {
        stack.push(s1);
      } else {

        if (stack.isEmpty()) {
          return false;
        }

        final String peek = stack.peek();

        if (s1.equals(")")) {
          if (peek.equals("(")) {
            stack.pop();
          } else {
            return false;
          }
        } else if (s1.equals("]")) {
          if (peek.equals("[")) {
            stack.pop();
          } else {
            return false;
          }
        } else if (s1.equals("}")) {
          if (peek.equals("{")) {
            stack.pop();
          } else {
            return false;
          }
        } else {
          return false;
        }
      }
    }

    return stack.isEmpty();
  }

  public static void main(String[] args) {
    final S020_ValidParentheses validParentheses = new S020_ValidParentheses();
    System.out.println(validParentheses.isValid("()"));
    System.out.println(validParentheses.isValid("()[]{}"));
    System.out.println(validParentheses.isValid("(]"));
    System.out.println(validParentheses.isValid("([)]"));
    System.out.println(validParentheses.isValid("["));
  }
}
