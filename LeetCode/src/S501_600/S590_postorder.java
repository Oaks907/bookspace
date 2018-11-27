package S501_600;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Create by haifei on 27/11/2018 11:15 PM.
 * Given an n-ary tree, return the postorder traversal of its nodes' values.
 */
public class S590_postorder {

  public static void main(String[] args) {
  }

  private List<Integer> result = new ArrayList<>();

  public List<Integer> postorder(Node root) {
    if (root == null) {
      return result;
    }

    recursion(root);
    return result;
  }

  private List<Integer> recursion(Node node) {
    if (node == null) {
      return result;
    }

    for (Node item : node.children) {
      recursion(item);
    }
    result.add(node.val);

    return result;
  }

  //解法2：使用栈
  public List<Integer> postorder2(Node root) {
    if (root == null) {
      return result;
    }

    Stack<Node> stack = new Stack<>();
    stack.add(root);

    while (!stack.isEmpty()) {
      Node node = stack.pop();
      result.add(node.val);

      for (Node item : node.children) {
        stack.push(item);
      }
    }
    Collections.reverse(result);

    return result;
  }


  class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
      val = _val;
      children = _children;
    }
  };
}
