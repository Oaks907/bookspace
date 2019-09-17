package S101_200;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * Create by haifei on 17/9/2019 11:42 PM.
 */
public class S116_PopulatingNextRightPointersinEachNode {

  public Node connect_1(Node root) {

    Queue<Node> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
      int size = queue.size();

      while (size-- > 0) {
        Node poll = queue.poll();
        if (null == poll) {
          continue;
        }
        if (poll.left != null) {
          queue.offer(poll.left);
        }
        if (poll.right != null) {
          queue.offer(poll.right);
        }
        if (size > 0) {
          poll.next = queue.peek();
        } else if (size == 0) {
          poll.next = null;
        }
      }
    }

    return root;
  }

  // solution Recursion
  public Node connect(Node root) {
    if(null == root) {
      return root;
    }
    connect(root.left, root.right);
    return root;
  }

  public void connect(Node x, Node y) {
    if (x == null || y == null) {
      return;
    }
    x.next = y;
    connect(x.left, x.right);
    connect(x.right, y.left);
    connect(y.left, y.right);
  }


  class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
      val = _val;
      left = _left;
      right = _right;
      next = _next;
    }
  }
}
