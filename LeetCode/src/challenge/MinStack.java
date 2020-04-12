package challenge;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * Create by haifei on 12/4/2020 9:51 PM.
 */
public class MinStack {

    private Stack<Integer> stack;
    private Queue<Integer> queue;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<>();
        queue = new PriorityQueue<>(Comparator.comparingInt(o -> o));
    }

    public void push(int x) {
        stack.push(x);
        queue.add(x);
    }

    public void pop() {
        Integer pop = stack.pop();
        queue.remove(pop);
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return queue.peek();
    }
}
