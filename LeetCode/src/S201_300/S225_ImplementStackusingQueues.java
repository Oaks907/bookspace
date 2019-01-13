package S201_300;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Create by haifei on 12/1/2019 8:26 PM.
 * <p>
 * 使用队列实现栈
 * <p>
 * https://leetcode.com/problems/implement-stack-using-queues/
 */
public class S225_ImplementStackusingQueues {

  private Queue<Integer> queue = new LinkedList();

  /**
   * Initialize your data structure here.
   */
  public S225_ImplementStackusingQueues() {

  }

  /**
   * Push element x onto stack.
   */
  public void push(int x) {
    queue.offer(x);

    for (int i = 1; i < queue.size(); i++) {
      queue.offer(queue.poll());
    }
  }

  /**
   * Removes the element on top of the stack and returns that element.
   */
  public int pop() {
    return queue.poll();
  }

  /**
   * Get the top element.
   */
  public int top() {
    return queue.peek();
  }

  /**
   * Returns whether the stack is empty.
   */
  public boolean empty() {
    return queue.isEmpty();
  }

  public static void main(String[] args) {
    final S225_ImplementStackusingQueues stack =
      new S225_ImplementStackusingQueues();

    stack.push(1);
    stack.push(2);
    System.out.println(stack.top());
    System.out.println(stack.pop());
    System.out.println(stack.empty());
  }

}
