package S001_100;

/**
 * Create by haifei on 4/12/2018 4:08 PM.
 * <p>
 * https://leetcode.com/problems/longest-palindromic-substring/submissions/
 * https://blog.csdn.net/u013309870/article/details/70742315
 */
public class S005_LongestPalindromicSubstring {

  //--------中心扩展-------
  public String str = "";

  public String longestPalindrome(String s) {

    //对于原 String 进行扩展, 使元素之间填充 # 号
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      sb.append("#");
      sb.append(s.charAt(i));
    }
    sb.append("#");

    final char[] chars = sb.toString().toCharArray();
    int maxCount = Integer.MIN_VALUE;
    for (int i = 0; i < chars.length; i++) {
      maxCount = helper(chars, i, maxCount);
    }

    return str;
  }

  private int helper(char[] arr, int middle, int maxCount) {
    int count = 0;
    int left = middle;
    int right = middle;
    while (left >= 0 && right < arr.length && arr[left] == arr[right]) {
      left--;
      right++;
      count++;
    }

    if (count > maxCount) {
      maxCount = count;
      str = getStringFromArr(arr, left + 1, right - 1).replace("#", "");
    }
    return maxCount;
  }

  private String getStringFromArr(char[] arr, int start, int end) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = start; i < end + 1; i++) {
      stringBuilder.append(arr[i]);
    }
    return stringBuilder.toString();
  }

  public static void main(String[] args) {
    final S005_LongestPalindromicSubstring longestPalindromicSubstring =
      new S005_LongestPalindromicSubstring();

    //中心填充 time:o(n^2)
    System.out.println(longestPalindromicSubstring.longestPalindrome("cbbd"));
    System.out.println(longestPalindromicSubstring.longestPalindrome("a"));
    System.out.println(longestPalindromicSubstring.longestPalindrome2("babad"));
    //动态规划
    System.out.println(longestPalindromicSubstring.longestPalindrome2("cbbd"));
    System.out.println(longestPalindromicSubstring.longestPalindrome2("babad"));
  }

  //------动态规划

  public String longestPalindrome2(String s) {
    if (null == s || s.equals("")) {
      return "";
    }

    final char[] chars = s.toCharArray();

    boolean[][] arr = new boolean[chars.length][chars.length];

    int maxCount = Integer.MIN_VALUE;
    String result = "";

    for (int j = 0; j < chars.length; j++) {
      arr[j][j] = true;
      for (int i = 0; i <= j; i++) {
        //在 chars[j] == chars[i]的基础上,i, j 相邻或者已经判断过 arr[i+1][j-1] 是回文，他就是回文
        arr[i][j] = (chars[j] == chars[i] && (j - i < 2 || arr[i + 1][j - 1]));
        if (arr[i][j]) {
          if (j - i > maxCount) {
            maxCount = j - i;
            result = getStringFromArr(chars, i, j);
          }
        }
      }
    }
    return result;
  }

}
