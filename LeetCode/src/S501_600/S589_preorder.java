package S501_600;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Create by haifei on 27/11/2018 11:35 PM.
 */
public class S589_preorder {

  //解法一：非递归解法

  List<Integer> result = new ArrayList();

  public List<Integer> preorder(Node root) {
    if (root == null) {
      return result;
    }
    Stack<Node> stack = new Stack();
    stack.push(root);

    while (!stack.isEmpty()) {
      Node node = stack.pop();
      result.add(node.val);

      List<Node> list = node.children;
      for (int i = list.size() - 1; i >= 0; i--) {
        stack.push(list.get(i));
      }
    }
    return result;
  }

  class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val, List<Node> _children) {
      val = _val;
      children = _children;
    }
  }
}
