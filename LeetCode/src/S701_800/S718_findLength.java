package S701_800;

/**
 * Create by haifei on 4/12/2018 8:50 PM.
 * https://leetcode.com/problems/maximum-length-of-repeated-subarray/
 */
public class S718_findLength {

  //通过矩阵找出规律，总结出状态转移方程
  public int findLength(int[] A, int[] B) {
    int col = A.length; //列
    int row = B.length; //行

    int[][] dp = new int[row][col];
    //初始化第一个行与第一列
    for (int i = 0; i < col; i++) {
      if (A[i] == B[0]) {
        dp[0][i] = 1;
      }
    }

    for (int i = 0; i < row; i++) {
      if (A[0] == B[i]) {
        dp[i][0] = 1;
      }
    }

    int maxLen = 0;

    for (int i = 1; i < row; i++) {
      for (int j = 1; j < col; j++) {
        if (A[j] == B[i]) {
          //状态转移，当前的状态依赖于上一个状态
          dp[i][j] = dp[i - 1][j - 1] + 1;
          //找出最大值
          if (maxLen < dp[i][j]) {
            maxLen = dp[i][j];
          }
        }
      }
    }

    return maxLen;
  }

  //---------对上面算法的优化-----------
  //使用一维数组代替二维数组.同时使用一个临时数组存储值
  //时间复杂度 o(min(m,n)) 空间复杂度o(2 * max(m,n))
  public int findLength2(int[] A, int[] B) {

    if (A.length < B.length) {
      return findLength2(B, A);
    }

    int len = A.length;
    int[] dp = new int[A.length];

    //初始化第一列
    for (int i = 0; i < len; i++) {
      if (B[0] == A[i]) {
        dp[i] = 1;
      }
    }

    int maxLen = 0;

    for (int i = 1; i < B.length; i++) { //行
      int[] temp = new int[len];
      for (int j = 1; j < len; j++) {
        //temp第一个节点
        if (B[i] == A[0]) {
          temp[0] = 1;
        }

        if (B[i] == A[j]) {
          temp[j] = dp[j - 1] + 1;
          if (temp[j] > maxLen) {
            maxLen = temp[j];
          }
        }
      }
      dp = temp;
    }

    return maxLen;
  }

  public static void main(String[] args) {
    final S718_findLength findLength = new S718_findLength();
    int[] A = {1, 2, 3, 2, 1};
    int[] B = {3, 2, 1, 4, 7};
    System.out.println(findLength.findLength(A, B));
    System.out.println(findLength.findLength2(A, B));

    A = new int[]{0, 0, 0, 0, 1};
    B = new int[]{1, 0, 0, 0, 0};
    System.out.println(findLength.findLength(A, B));
    System.out.println(findLength.findLength2(A, B));
  }
}
