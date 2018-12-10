package com.haifei.tree;

/**
 * Create by haifei on 17/11/2018 11:28 PM.
 */
public class S21_numTree {

  int numTrees(int n) {
    int[] counts = new int[n + 2];
    counts[0] = 1;
    counts[1] = 1;
    for (int i = 2; i <= n; i++) {
      for (int j = 0; j < i; j++) {
        counts[i] += counts[j] * counts[i - j - 1];
      }
    }
    return counts[n];
  }

  public static void main(String[] args) {
   S21_numTree tree = new S21_numTree();
   for (int i = 0; i <= 5; i++) {
     System.out.println(tree.numTrees(i));
   }
  }
}
