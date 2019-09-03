package S001_100;

/**
 * Create by haifei on 2/9/2019 11:44 PM.
 */
public class S096_UniqueBinarySearchTrees {

  public int numTrees(int n) {
    int[] dp = new int[n + 1];
    dp[0] = dp[1] = 1;

    for (int i = 2; i <= n; i++) {
      for (int j = 0; j < i; j ++) {
        dp[i] += dp[j] * dp[i - j - 1];
      }
    }
    return dp[n];
  }

  public static void main(String[] args) {
    S096_UniqueBinarySearchTrees searchTrees = new S096_UniqueBinarySearchTrees();
    System.out.println(searchTrees.numTrees(4));
  }
}
