package model;

import java.util.List;

/**
 * Create by haifei on 5/12/2018 12:30 AM.
 */
public class Node {
  public int val;
  public List<Node> children;

  public Node() {
  }

  public Node(int _val, List<Node> _children) {
    val = _val;
    children = _children;
  }
}
