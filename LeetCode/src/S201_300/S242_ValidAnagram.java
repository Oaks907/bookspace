package S201_300;

/**
 * Create by haifei on 13/1/2019 3:39 PM.
 *
 * 变位词
 *
 * https://leetcode.com/problems/valid-anagram/
 *
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 * Output: false
 */
public class S242_ValidAnagram {

  public boolean isAnagram(String s, String t) {
    int[] firstArr = new int[26];
    int[] lastArr = new int[26];

    for (int i = 0; i < s.length(); i++) {
      firstArr[s.charAt(i) - 'a'] += 1;
    }

    for (int i = 0; i < t.length(); i++) {
      lastArr[t.charAt(i) - 'a'] += 1;
    }

    for (int i = 0; i < firstArr.length; i++) {
      if (firstArr[i] != lastArr[i]) {
        return false;
      }
    }

    return true;
  }
}
