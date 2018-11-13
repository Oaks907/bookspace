package com.haifei;

import jdk.nashorn.internal.ir.IfNode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Create by haifei on 31/10/2018 1:22 PM.
 */
public class S43_DicesProbability_recursion {


  /**
   * https://blog.csdn.net/K346K346/article/details/50988681
   *
   * @param n   骰子的个数
   * @param sum 指定的点和数
   * @return
   */
  private int getNSumCount(int n, int sum) {

    if (n < 1 || sum < n || sum > 6 * n) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }
    return getNSumCount(n - 1, sum - 1) + getNSumCount(n - 1, sum - 2)
      + getNSumCount(n - 1, sum - 3) + getNSumCount(n - 1, sum - 4)
      + getNSumCount(n - 1, sum - 5) + getNSumCount(n - 1, sum - 6);
  }


  private static void mainRecursionOne(int n) {
    final S43_DicesProbability_recursion obj =
      new S43_DicesProbability_recursion();

    for (int i = n; i <= 6 * n; i++) {
      System.out.println("f(" + n + "," + i + ")=" + obj.getNSumCount(n, i));
    }
  }


  public static void main(String[] args) {
    mainRecursionOne(3);
    printProbability2(3);
  }


  public static int g_maxValue =6;
//https://github.com/nibnait/algorithms/blob/master/src/SwordOffer/f43_n%E4%B8%AA%E9%AA%B0%E5%AD%90%E7%9A%84%E7%82%B9%E6%95%B0.java
  private static void printProbability2(int n) {
    if (n <= 0) {
      return;
    }
    int maxSum = n * g_maxValue;
    int[][] probabilities = new int[2][maxSum + 1];
    //数组初始化
    for (int i = 0; i < maxSum + 1; i++) {
      probabilities[0][i] = 0;
      probabilities[1][i] = 0;
    }
    //第1个骰子的6种情况
    for (int i = 1; i <= g_maxValue; i++) {
      probabilities[0][i] = 1;
    }
    //开始抛剩下的骰子
    int flag = 0;   //标记哪一行刚刚被使用过
    for (int k = 2; k <= n; k++) {
      // 如果抛出了k个骰子，那么和为[0, k-1]的出现次数为0
      for (int i = 0; i < k; i++) {
        probabilities[1 - flag][i] = 0;
      }
      for (int i = k; i <= g_maxValue * k; i++) {
        probabilities[1 - flag][i] = 0;   //先将此处初始化为0
        //统计和为i时的点数出现的次数
        for (int j = 1; j <= i && j <= g_maxValue; j++) {
          probabilities[1 - flag][i] += probabilities[flag][i - j];
        }
      }
      flag = 1 - flag;
    }
    printProbability(n, maxSum, probabilities, flag);
  }

  private static void printProbability(int n, int maxSum, int[][] probabilities, int flag) {
    double total = Math.pow(g_maxValue, n);
    for (int i = n; i < maxSum + 1; i++) {
      double ratio = probabilities[flag][i] / total;
      System.out.print("P(" + i + ")=");
      System.out.println(probabilities[flag][i]);
      if (i != maxSum) {
        System.out.print(", ");
      }
    }

  }


}
