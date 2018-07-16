package com.haifei;

import java.util.Stack;

/**
 * Create by haifei on 16/7/2018.
 */
public class S07_QueueWithTwoStacks {

  private Stack<Integer> stack1 = new Stack<>();
  private Stack<Integer> stack2 = new Stack<>();

  public S07_QueueWithTwoStacks() {

  }

  /** Push element x to the back of queue. */
  public void push(int x) {
    stack1.push(x);
  }

  /** Removes the element from in front of queue and returns that element. */
  public int pop() {
    while (!stack1.isEmpty()) {
      stack2.push(stack1.pop());
    }
    int value = stack2.pop();
    while (!stack2.isEmpty()) {
      stack1.push(stack2.pop());
    }
    return value;
  }

  /** Get the front element. */
  public int peek() {
    while (!stack1.isEmpty()) {
      stack2.push(stack1.pop());
    }
    int value = stack2.peek();
    while (!stack2.isEmpty()) {
      stack1.push(stack2.pop());
    }
    return value;
  }

  /** Returns whether the queue is empty. */
  public boolean empty() {
    return stack1.isEmpty();
  }
}
