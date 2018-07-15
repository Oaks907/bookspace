package S001_100;

/**
 * Create by haifei on 22/1/2018.
 */
public class S003_LongestSubstringWIthoutRepeatingCharacters {
  public static int lengthOfLongestSubstring(String s) {
    int maxResult = 0;
    int tempValue = 0;
    String[] strArrays = s.split("");
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < strArrays.length; i++) {
      if (String.valueOf(stringBuilder).contains(strArrays[i])) {
        maxResult = Math.max(maxResult, tempValue);
        int temp = stringBuilder.indexOf(strArrays[i]);
        tempValue = tempValue - temp > 0 ? tempValue - temp : 1;  //关健
        stringBuilder = stringBuilder.delete(0, temp + 1).append(strArrays[i]);
      } else {
        tempValue++;
        maxResult = Math.max(maxResult, tempValue);
        stringBuilder.append(strArrays[i]);
      }
    }
    return maxResult;
  }

  public static void main(String[] args) {
    System.out.println(lengthOfLongestSubstring("abcabcbb"));
    System.out.println(lengthOfLongestSubstring("bbbbb"));
    System.out.println(lengthOfLongestSubstring("dvdf"));
    System.out.println(lengthOfLongestSubstring("pwwkew"));
  }
}
