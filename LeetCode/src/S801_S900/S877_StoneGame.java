package S801_S900;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create by haifei on 30/11/2018 11:53 PM.
 * https://leetcode.com/problems/stone-game/
 */
public class S877_StoneGame {

  /**
   * 先排序后间隔取值
   *
   * @param piles
   * @return
   */
  public boolean stoneGame(int[] piles) {
    Arrays.sort(piles);

    int length = piles.length - 1;

    final int[] dp = new int[piles.length];
    dp[length] = piles[length];
    dp[length - 1] = piles[length - 1];

    for (int i = length - 2; i >= 0; i--) {
      dp[i] = dp[i + 2] + piles[i];
    }

    return dp[1] > dp[0];
  }

  public boolean stoneGame2(int[] piles) {

    List<Integer> list = arrayToList(piles);
    return doSomething(list, 0, 0);
  }

  private boolean doSomething(List<Integer> list, int aScore, int lScore) {
    if (list.size() == 0) {
      return aScore > lScore;
    }
    int left = list.remove(0);
    int right = list.remove(list.size() - 1);

    if (left >= right) {
      return  doSomething(list, aScore + left , lScore + right);
    } else {
      return  doSomething(list, aScore + right, lScore + left);
    }
  }

  private List<Integer> arrayToList(int[] arr) {

    List<Integer> list = new ArrayList<>();

    for (int i = 0; i < arr.length; i++) {
      list.add(arr[i]);
    }

    return list;
  }


  public static void main(String[] args) {
    final S877_StoneGame stoneGame = new S877_StoneGame();
    int[] arr = {5, 3, 4, 5};
    stoneGame.stoneGame(arr);
    System.out.println(stoneGame.stoneGame2(arr));
  }
}
