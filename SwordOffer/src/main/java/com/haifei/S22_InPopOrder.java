package com.haifei;

import java.util.Stack;

/**
 * Create by haifei on 16/7/2018.
 */
public class S22_InPopOrder {

  public boolean isPopOrder(int[] push, int[] pop) throws Exception {
    if (null == push || null == pop || push.length != pop.length) {
      throw new Exception("Illegal Exception");
    }

    boolean isPopOrder = false;
    int pushIndex = 0;
    int popIndex = 0;
    Stack<Integer> stack = new Stack<>();
    while (popIndex < pop.length) {

      while (stack.isEmpty() || stack.peek() != pop[popIndex]) {
        if (pushIndex >= push.length ) {
          break;
        }
        stack.push(push[pushIndex]);
        pushIndex++;
      }

      if (stack.peek() != pop[popIndex]) {
        break;
      }
      stack.pop();
      popIndex++;
    }

    if (stack.isEmpty() && popIndex == pop.length) {
      isPopOrder = true;
    }

    return isPopOrder;
  }

  public static void main(String[] args) throws Exception {
    int[] push = {1, 2, 3, 4, 5};
    int[] pop = {4, 5, 3, 2, 1};
    S22_InPopOrder inPopOrder = new S22_InPopOrder();
    System.out.println(inPopOrder.isPopOrder(push, pop));
  }
}
