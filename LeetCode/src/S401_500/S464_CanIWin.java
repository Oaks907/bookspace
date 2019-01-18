package S401_500;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by haifei on 17/1/2019 8:26 AM.
 * <p>
 * https://leetcode.com/problems/can-i-win/
 * <p>
 * In the "100 game," two players take turns adding, to a running total, any integer from 1..10.
 * The player who first causes the running total to reach or exceed 100 wins.
 * <p>
 * What if we change the game so that players cannot re-use integers?
 * <p>
 * For example, two players might take turns drawing from a common pool of numbers of 1..15
 * without replacement until they reach a total >= 100.
 * <p>
 * Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first
 * player to move can force a win, assuming both players play optimally.
 * <p>
 * You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal
 * will not be larger than 300.
 */
public class S464_CanIWin {

  /**
   * Time Limit Exceeded
   * 时间复杂度 ： n * (n - 1) * (n - 2) = n ^ n
   *
   * @param maxChoosableInteger
   * @param desiredTotal
   * @return
   */
  public boolean canIWin(int maxChoosableInteger, int desiredTotal) {

    if (maxChoosableInteger > desiredTotal) {
      return true;
    }

    int[] arr = new int[maxChoosableInteger];

    return helper(arr, desiredTotal, 0, 0);
  }

  /**
   * @param arr     记录数字有没有被选择
   * @param desired 期望值
   * @param cur     当前值
   * @param level   选择层级，奇数代表第一个
   * @return
   */
  public boolean helper(int[] arr, int desired, int cur, int level) {

    //第一个人先选，当达到目标值，且层级为奇数（先选）
    if (cur >= desired) {
      return level % 2 == 1;
    }

    boolean result = true;

    //遍历所有的选择情况，如果存在一种都是true的情况下，证明无论怎么选都是能赢的
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] != 1) {

        arr[i] = 1;

        int temp = cur + i + 1;
        if (level != 0) {
          result &= helper(arr, desired, temp, level + 1);
        } else {
          result = helper(arr, desired, temp, level + 1);
          if (result) {
            return true;
          }
        }

        arr[i] = 0;
      }
    }

    return result;
  }


  private boolean[] used;
  private Map<Integer, Boolean> map;

  /**
   * 使用 Hash进行优化：
   * 优化的思路
   *
   * @param maxChoosableInteger
   * @param desiredTotal
   * @return
   */
  public boolean canIWin_map(int maxChoosableInteger, int desiredTotal) {

    int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
    if (sum < desiredTotal) {
      return false;
    }
    if (maxChoosableInteger > desiredTotal) {
      return true;
    }

    used = new boolean[maxChoosableInteger + 1];
    map = new HashMap<>();

    System.out.println(format(used));

    return helper(desiredTotal);
  }

  public boolean helper(int desiredTotal) {

    int key = format(used);//注意这个不是 desiredTotal 值，它代表的是排列顺序，是唯一的

    if (!map.containsKey(key)) {
      // try every unchosen number as next step
      for (int i = 1; i < used.length; i++) {
        if (!used[i]) {
          used[i] = true;
          // check whether this lead to a win (i.e. the other player lose)
          //如果我拿了当前的超过了给定的值 || 如果对手不赢 !helper.我就赢了
          if (desiredTotal - i <= 0 || !helper(desiredTotal - i)) {
            map.put(key, true);
            used[i] = false;
            System.out.println(i + " true " + map + " " + (desiredTotal - i));
            return true;
          }
          used[i] = false;
        }
      }
//      System.out.println( " false " + map + " ");
      map.put(key, false);
    }
    return map.get(key);
  }

  // transfer boolean[] to an Integer
  public int format(boolean[] used) {
    int num = 0;
    for (boolean b : used) {
      num <<= 1;
      if (b) num |= 1;
    }
    return num;
  }


  public static void main(String[] args) {
    final S464_CanIWin canIWin = new S464_CanIWin();
//    System.out.println(canIWin.canIWin(10, 11));
//    System.out.println(canIWin.canIWin(3, 6));
//    System.out.println(canIWin.canIWin(4, 6));
//    System.out.println(canIWin.canIWin(18, 79));


//    System.out.println(canIWin.canIWin_map(10, 11));
//    System.out.println(canIWin.canIWin_map(3, 6));
    System.out.println(canIWin.canIWin_map(4, 6));
//    System.out.println(canIWin.canIWin_map(18, 79));
  }


}
