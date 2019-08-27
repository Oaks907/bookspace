package S1101_S1200;

import java.util.Arrays;

/**
 * Create by haifei on 27/8/2019 12:18 AM.
 */
public class S1160_FindWordsThatCanBeFormedbyCharacters {

  public int countCharacters(String[] words, String chars) {
    return Arrays.stream(words).filter(item -> helper(item, chars)).mapToInt(String::length).sum();
  }

  public boolean helper(String word, String chars) {
    String[] split = word.split("");

    for (String item : split) {
      if (chars.contains(item)) {
        chars = chars.replaceFirst(item, "");
      } else {
        return false;
      }
    }

    return true;
  }
}
