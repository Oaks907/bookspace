package S401_500;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Create by haifei on 23/3/2019 5:56 PM.
 */
public class S429_NaryTreeLevelOrderTraversal {


  public static void main(String[] args) {

  }

  public List<List<Integer>> levelOrder(Node root) {

    List<List<Integer>> result = new ArrayList();

    if (root == null) {
      return result;
    }

    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {

      int size = queue.size();
      List<Integer> list = new ArrayList<>();

      while (size-- > 0) {
        final Node poll = queue.poll();
        list.add(poll.val);
        if (poll.children.size() > 0) {
          queue.addAll(poll.children);
        }
      }
      result.add(list);
    }

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
