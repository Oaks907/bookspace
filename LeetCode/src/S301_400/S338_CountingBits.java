package S301_400;

import utils.PrintUtils;

/**
 * Create by haifei on 30/11/2018 9:14 PM.
 */
public class S338_CountingBits {

  public int[] countBits(int num) {
    int index = 1;
    int[] dp = new int[num + 1];
    dp[0] = 0;
//    if (num == 0) {
//      return dp;
//    }
//    dp[1] = 1;
//    if (num == 1) {
//      return dp;
//    }

    for (int i = 1; i <= num; i++) {
      if ((i & (i - 1)) == 0) {
        dp[i] = 1;
        index = i;
        continue;
      }
      dp[i] = dp[index] + dp[i - index];
    }
    return dp;
  }

  //上面虽然找到了规律，但是使用位运算会更加方便
  //状态转移方程是f(i) = f(i/2) + i % 2;
  public int[] countBits2(int num) {
    int[] dp = new int[num + 1];

    for (int i = 0; i <= num; i++) {
      dp[i] = dp[i >> 1] + i % 2;
    }
    return dp;
  }

  public static void main(String[] args) {

    final S338_CountingBits countingBits = new S338_CountingBits();

    PrintUtils.printArray(countingBits.countBits(8));
    PrintUtils.printArray(countingBits.countBits2(8));

//    for (int i = 0; i < 10; i++) {
//      Utils.printArray(countingBits.countBits(i));
//    }
  }


}
