package S601_700;

/**
 * Create by haifei on 4/12/2018 1:26 PM.
 */
public class S647_PalindromicSubstrings {

  public int countSubstrings(String s) {

    if (null == s) {
      return 0;
    }

    String[] arr = s.split("");

    int count = 0;
    for (int i = 0; i < arr.length; i++) {
      count = helper(arr, i, i, count);
      count = helper(arr, i, i + 1, count);
    }

    return count;
  }

  private int helper(String[] arr, int left, int right, int count) {
    while (left >= 0 && right < arr.length && arr[left].equals(arr[right])) {
      left--;
      right++;
      count++;
    }
    return count;
  }

  public static void main(String[] args) {

    final S647_PalindromicSubstrings palindromicSubstrings = new S647_PalindromicSubstrings();

    System.out.println(palindromicSubstrings.countSubstrings("abc"));
    System.out.println(palindromicSubstrings.countSubstrings("aaa"));
    palindromicSubstrings.countSubstrings2("ababa");
  }

  public int countSubstrings2(String s) {
    int n = s.length();
    int res = 0;
    boolean[][] dp = new boolean[n][n];

    for (int i = n - 1; i >= 0; i--) {
      for (int j = i; j < n; j++) {
        dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
        if (dp[i][j]) {
          res++;
        }
      }
    }
    return res;
  }
}
