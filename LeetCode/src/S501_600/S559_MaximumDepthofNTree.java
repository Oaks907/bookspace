package S501_600;

import model.Node;

import java.util.List;

/**
 * Create by haifei on 5/12/2018 12:30 AM.
 */
public class S559_MaximumDepthofNTree {

  public int maxDepth(Node root) {
    return  findDepth(root);
  }

  public int findDepth(Node root) {
    if (root == null) {
      return 0;
    }
    final List<Node> children = root.children;
    int maxDepth = 0;
    for (Node node : children) {
      maxDepth = Math.max(maxDepth, findDepth(node));
    }
    return maxDepth + 1;
  }


  public static void main(String[] args) {

  }
}
