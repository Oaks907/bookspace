package com.haifei;

import java.util.Stack;

/**
 * Create by haifei on 16/7/2018.
 */
public class S20_StackWithMin {

  Stack<Integer> stack;
  Stack<Integer> minStack;

  /**
   * initialize your data structure here.
   */
  public S20_StackWithMin() {
    stack = new Stack();
    minStack = new Stack();
  }

  public void push(int x) {
    stack.push(x);
    if (minStack.isEmpty() || minStack.peek() >= x) {
      minStack.push(x);
    }
  }

  public void pop() {
    int val = stack.pop();
    if (val == minStack.peek()) {
      minStack.pop();
    }
  }

  public int top() {
    return stack.peek();
  }

  public int getMin() {
    return minStack.peek();
  }
}
