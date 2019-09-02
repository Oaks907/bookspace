package S1101_S1200;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 1/9/2019 11:44 AM.
 */
public class S1178_NumberofValidWordsforEachPuzzle {

  public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {

    int[] convertedWords = new int[words.length];

    for (int i = 0; i < words.length; i++) {
      int chars = 0;
      for (int j = 0; j < words[i].length(); j++) {
        chars |= 1 << (words[i].charAt(j) - 'a');
      }
      convertedWords[i] = chars;
    }

    List<Integer> result = new ArrayList<>(puzzles.length);
    for (String puzzle : puzzles) {
      char first = puzzle.charAt(0);
      int chars = 0;
      for (int i = 0; i < puzzle.length(); i++) {
        chars |= 1 << (puzzle.charAt(i) - 'a');
      }

      int num = 0;
      int firstMask = 1 << first - 'a';
      for (int word : convertedWords) {
        if ((word & chars) == word && (firstMask & word) == firstMask) {
          num++;
        }
      }
      result.add(num);
    }

    return result;
  }

  @Test
  public void test() {
    S1178_NumberofValidWordsforEachPuzzle puzzle =
      new S1178_NumberofValidWordsforEachPuzzle();

    String[] words = {"aaaa", "asas", "able", "ability", "actt", "actor", "access"};
    String[] puzzles = {"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"};

    System.out.println(puzzle.findNumOfValidWords(words, puzzles));
  }
}
