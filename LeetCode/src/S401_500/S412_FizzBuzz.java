package S401_500;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/fizz-buzz/
 * Create by haifei on 20/9/2019 12:17 AM.
 */
public class S412_FizzBuzz {

  public List<String> fizzBuzz(int n) {

    List<String> result = new ArrayList<>();

    for (int i = 1; i <= n; i++) {
      if (i % 3 == 0 && i % 5 == 0) {
        result.add("FizzBuzz");
      } else if (i % 3 == 0) {
        result.add("Fizz");
      } else if (i % 5 == 0) {
        result.add("Buzz");
      } else {
        result.add(String.valueOf(i));
      }
    }

    return result;
  }

  @Test
  public void test() {
    List<String> result = fizzBuzz(15);
    System.out.println(result);
  }
}
