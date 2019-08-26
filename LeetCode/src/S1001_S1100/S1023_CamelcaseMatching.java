package S1001_S1100;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 9/4/2019 11:26 AM.
 */
public class S1023_CamelcaseMatching {

  public List<Boolean> camelMatch(String[] queries, String pattern) {

    List<Boolean> result = new ArrayList<>();

    if (queries == null || queries.length == 0) {
      return result;
    }

    final char[] chars = pattern.toCharArray();

    for (int i = 0; i < queries.length; i++) {
      result.add(wordCanMatch(queries[i], chars));
    }

    return result;
  }

  private boolean wordCanMatch(String word, char[] patternArr) {

    int patternIndex = 0;

    for (int i = 0; i < word.length(); i++) {

      int curChar = word.charAt(i);

      if (curChar == patternArr[patternIndex]) {

        patternIndex++;

        if (patternIndex >= patternArr.length) {

          for (int j = i + 1; j < word.length(); j++) {
            if (Character.isUpperCase(word.charAt(j))) {
              return false;
            }
          }
          break;
        }
      } else {
        if (Character.isUpperCase(curChar)) {
          return false;
        }
      }
    }

    return patternIndex >= patternArr.length;
  }

  S1023_CamelcaseMatching camelcaseMatching;

  @Before
  public void before() {
    camelcaseMatching = new S1023_CamelcaseMatching();
  }

  @Test
  public void test3() {

    String[] str = {"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"};

    System.out.println(camelcaseMatching.camelMatch(str, "FoBaT"));
  }

  @Test
  public void test2() {

    String[] str = {"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"};

    System.out.println(camelcaseMatching.camelMatch(str, "FoBa")); //[true,false,true,false,false]
  }

  @Test
  public void test1() {

    String[] str = {"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"};

    System.out.println(camelcaseMatching.camelMatch(str, "FB")); //[true, false, true, true, false]
  }
}
