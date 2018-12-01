package S801_S900;

import java.util.Arrays;

/**
 * Create by haifei on 30/11/2018 11:53 PM.
 * https://leetcode.com/problems/stone-game/
 */
public class S877_StoneGame {

  public boolean stoneGame(int[] piles) {
    Arrays.sort(piles);

    int length = piles.length - 1;

    final int[] dp = new int[piles.length];
    dp[length] = piles[length];
    dp[length - 1] = piles[length - 1];

    for (int i = length - 2; i >=0 ; i--) {
      dp[i] = dp[i + 2] + piles[i];
    }

    return dp[1] > dp[0];
  }

  public static void main(String[] args) {
    final S877_StoneGame stoneGame = new S877_StoneGame();
    int[] arr = {5,3,4,5};
    stoneGame.stoneGame(arr);
  }
}
